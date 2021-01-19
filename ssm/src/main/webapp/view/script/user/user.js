const APP_NAME = "/ssm";
let pageSizeSel, navigatePageNumsOl;
let pageNum, pageSize, total, pages, navigatePageNums;

$(() => {
    pageSizeSel = $("#paging-size-sel").change(() => changePageSize());
    $("#delete-btn").click(() => deleteByIds());
    $("#insert-btn").click(() => insert());
    paging(1, 5);
});

function insert() {
    location.href = `${APP_NAME}/view/html/user/user-insert.html`
}

function changePageSize() {
    paging(1, pageSizeSel.val())
}

function deleteByIds() {
    if (confirm("确认删除吗？")) {
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
}

function paging(pageNum, pageSize) {
    $.ajax({
        "url": `${APP_NAME}/api/user/paging`,
        "type": "post",
        "data": {"pageNum": pageNum, "pageSize": pageSize},
        "success": response => {
            if (response["status"] === 200) {
                renderTable(response["data"]["list"]);
                renderPaging(response["data"]);
            } else {
                alert(response["msg"]);
            }
        }
    });
}

function renderTable(users) {
    let userListTHead = $("#user-list-thead").html("");
    let headTr = $(`<tr></tr>`).appendTo(userListTHead);
    $(`<th><label><input id="select-all-cbx" type="checkbox"/></label></th>`).appendTo(headTr);
    $(`<th>序号</th><th>姓名</th><th>年龄</th><th>性别</th><th>操作</th>`).appendTo(headTr);
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

function renderPaging(pageInfo) {
    pageNum = pageInfo["pageNum"];
    pageSize = pageInfo["pageSize"];
    pages = pageInfo["pages"];
    total = pageInfo["total"];
    navigatePageNums = pageInfo["navigatepageNums"];
    navigatePageNumsOl = $("#paging-numbers-ol").html("");
    renderPrevAndFirstBtn();
    renderNavigatePageNums();
    renderLastAndNextBtn();
    renderPagingMessage();
    updatePagingSize();
}

function renderPrevAndFirstBtn() {
    if (pageNum === 1) {
        $(`<li class="disabled"><a>&laquo;</a></li>`).appendTo(navigatePageNumsOl);
        $(`<li class="disabled"><a>首页</a></li>`).appendTo(navigatePageNumsOl);
    } else {
        $(`<li class="item"><a>&laquo;</a></li>`).click(() => paging(pageNum - 1, pageSize)).appendTo(navigatePageNumsOl);
        $(`<li class="item"><a>首页</a></li>`).click(() => paging(1, pageSize)).appendTo(navigatePageNumsOl);
    }
}

function renderNavigatePageNums() {
    $.each(navigatePageNums, (i, v) => {
        let li = $(`<li class="item"><a>${v}</a></li>`).appendTo(navigatePageNumsOl);
        if (v === pageNum) {
            li.addClass("active");
        } else {
            li.click(() => paging(v, pageSize));
        }
    });
}

function renderLastAndNextBtn() {
    if (pageNum === pages) {
        $(`<li class="disabled"><a>尾页</a></li>`).appendTo(navigatePageNumsOl);
        $(`<li class="disabled"><a>&raquo;</a></li>`).appendTo(navigatePageNumsOl);
    } else {
        $(`<li class="item"><a>尾页</a></li>`).click(() => paging(pages, pageSize)).appendTo(navigatePageNumsOl);
        $(`<li class="item"><a>&raquo;</a></li>`).click(() => paging(pageNum + 1, pageSize)).appendTo(navigatePageNumsOl);
    }
}

function renderPagingMessage() {
    $(`<li><a>当前是第&nbsp;${pageNum}&nbsp;/&nbsp;${pages}&nbsp;页，共&nbsp;${total}&nbsp;条数据</a></li>`).appendTo(navigatePageNumsOl);
}

function updatePagingSize() {
    pageSizeSel.val(pageSize);
}
