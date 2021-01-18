const APP_NAME = "/ssm";
let pagingSizeSel;
let pagingNumbersOl;
let page, size, total, pages, numbers;

$(() => {
    pagingSizeSel = $("#paging-size-sel").change(() => changePagingSize());
    $("#delete-btn").click(() => {
        if (confirm("确认删除吗？")) {
            deleteByIds();
        }
    });
    $("#insert-btn").click(() => insert());
    paging(1, 5);
});

function insert() {
    location.href = `${APP_NAME}/view/html/user/user-insert.html`
}

function changePagingSize() {
    paging(1, pagingSizeSel.val())
}

function deleteByIds() {
    $.ajax({
        "url": `${APP_NAME}/api/user/delete-by-ids`,
        "type": "post",
        "data": $("#delete-form").serialize(),
        "success": response => {
            alert(response["msg"]);
            if (response["status"] === 200) {
                location.href = `${APP_NAME}/view/html/user/user.html`;
            }
        }
    });
}

function paging(page, size) {

    $.ajax({
        "url": `${APP_NAME}/api/user/paging`,
        "type": "post",
        "data": {"page": page, "size": size},
        "success": response => {
            if (response["status"] === 200) {
                renderTable(response["data"]["users"]);
                renderPaging(response["data"]["pagingUtil"]);
            } else {
                alert(response["msa"]);
            }
        }
    });
}

function renderTable(users) {
    buildTHead();
    let userListTbody = $("#user-list-tbody").html("");
    $.each(users, (i, v) => {
        let tr = $("<tr></tr>").appendTo(userListTbody);
        $(`<td><label><input name="ids" type="checkbox" value='${v["id"]}'/></td></label>`).appendTo(tr);
        $(`<td>${i + 1}</td>`).appendTo(tr);
        $(`<td>${v["name"] !== undefined ? v["name"] : ""}</td>`).appendTo(tr);
        $(`<td>${v["age"] !== undefined ? v["age"] : ""}</td>`).appendTo(tr);
        $(`<td>${v["gender"] === 1 ? "男" : v["gender"] === 0 ? "女" : v["gender"] === 2 ? "保密" : ""}</td>`).appendTo(tr);
        let updateTd = $(`<td></td>`).appendTo(tr);
        let updateBtn = $(`<a href="javascript:" >修改</a>`);
        updateBtn.click(() => {
            sessionStorage.setItem("id", v["id"]);
            location.href = `${APP_NAME}/view/html/user/user-update.html`;
        }).appendTo(updateTd);
    });
}

function buildTHead() {
    let userListTHead = $("#user-list-thead").html("");
    let headTr = $(`<tr></tr>`).appendTo(userListTHead);
    $(`<th><label><input id="select-all-cbx" type="checkbox"/></label></th>`).appendTo(headTr);
    $(`<th>序号</th>`).appendTo(headTr);
    $(`<th>姓名</th>`).appendTo(headTr);
    $(`<th>年龄</th>`).appendTo(headTr);
    $(`<th>性别</th>`).appendTo(headTr);
    $(`<th>操作</th>`).appendTo(headTr);
}

function renderPaging(pagingUtil) {
    page = parseInt(pagingUtil["page"]);
    size = parseInt(pagingUtil["size"]);
    pages = parseInt(pagingUtil["pages"]);
    total = parseInt(pagingUtil["total"]);
    numbers = pagingUtil["numbers"];
    pagingNumbersOl = $("#paging-numbers-ol").html("");
    renderPrevAndFirstBtn();
    renderNumbers();
    renderLastAndNextBtn();
    renderPagingMessage();
    updatePagingSize();
}

function renderPrevAndFirstBtn() {
    if (page === 1) {
        $(`<li class="disabled"><a>&laquo;</a></li>`).appendTo(pagingNumbersOl);
        $(`<li class="disabled"><a>首页</a></li>`).appendTo(pagingNumbersOl);
    } else {
        $(`<li class="item"><a>&laquo;</a></li>`).click(() => paging(page - 1, size)).appendTo(pagingNumbersOl);
        $(`<li class="item"><a>首页</a></li>`).click(() => paging(1, size)).appendTo(pagingNumbersOl);
    }
}

function renderLastAndNextBtn() {
    if (page === pages) {
        $(`<li class="disabled"><a>尾页</a></li>`).appendTo(pagingNumbersOl);
        $(`<li class="disabled"><a>&raquo;</a></li>`).appendTo(pagingNumbersOl);
    } else {
        $(`<li class="item"><a>尾页</a></li>`).click(() => paging(pages, size)).appendTo(pagingNumbersOl);
        $(`<li class="item"><a>&raquo;</a></li>`).click(() => paging(page + 1, size)).appendTo(pagingNumbersOl);
    }
}

function renderNumbers() {
    $.each(numbers, (i, v) => {
        let li = $(`<li class="item"><a>${v}</a></li>`).appendTo(pagingNumbersOl);
        if (parseInt(v) === page) {
            li.addClass("active");
        } else {
            li.click(() => paging(parseInt(v), size));
        }
    });
}

function renderPagingMessage() {
    $(`<li><a>当前是第&nbsp;${page}&nbsp;/&nbsp;${pages}&nbsp;页，共&nbsp;${total}&nbsp;条数据</a></li>`).appendTo(pagingNumbersOl);
}

function updatePagingSize() {
    pagingSizeSel.val(size);
}
