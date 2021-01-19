package com.joezhou.sm;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.joezhou.mapper.PigMapper;
import com.joezhou.pojo.Pig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;

/**
 * @author JoeZhou
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/spring-pig.xml")
public class PigTest {

    @Autowired
    private PigMapper pigMapper;

    @Test
    public void paging() {
        // 只有仅随startPage()的pageInfoA的分页生效
        // 若想pageInfoB生效需要再次在pageInfoB紧随之前调用startPage()
        PageHelper.startPage(2, 5);
        PageInfo<Pig> pageInfoA = new PageInfo<>(pigMapper.paging());
        PageInfo<Pig> pageInfoB = new PageInfo<>(pigMapper.paging());

        System.out.println("pageInfoA.pigs: " + pageInfoA.getList());
        System.out.println("\ttotal: " + pageInfoA.getTotal());
        System.out.println("\tpages: " + pageInfoA.getPages());
        System.out.println("\tpageNum: " + pageInfoA.getPageNum());
        System.out.println("\tpageSize: " + pageInfoA.getPageSize());
        System.out.println("\tlastPageNums: " + Arrays.toString(pageInfoA.getNavigatepageNums()));

        System.out.println("pageInfoB.pigs: " + pageInfoB.getList());
        System.out.println("\ttotal: " + pageInfoB.getTotal());
        System.out.println("\tpages: " + pageInfoB.getPages());
        System.out.println("\tpageNum: " + pageInfoB.getPageNum());
        System.out.println("\tpageSize: " + pageInfoB.getPageSize());
        System.out.println("\tlastPageNums: " + Arrays.toString(pageInfoB.getNavigatepageNums()));
    }

    @Test
    public void pageSizeZero() {
        PageHelper.startPage(1, 0);
        PageInfo<Pig> pageInfo = new PageInfo<>(pigMapper.paging());
        System.out.println("pigs: " + pageInfo.getList());
        System.out.println("pigs.total: " + pageInfo.getTotal());
    }

    @Test
    public void reasonable() {
        PageHelper.startPage(-1, 5);
        PageInfo<Pig> pageInfoA = new PageInfo<>(pigMapper.paging());
        PageHelper.startPage(1000, 5);
        PageInfo<Pig> pageInfoB = new PageInfo<>(pigMapper.paging());

        System.out.println("pageInfoA.pageNum: " + pageInfoA.getPageNum());
        System.out.println("pageInfoB.pageNum: " + pageInfoB.getPageNum());
    }

}