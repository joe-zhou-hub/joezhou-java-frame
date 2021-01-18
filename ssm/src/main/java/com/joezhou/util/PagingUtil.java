package com.joezhou.util;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author JoeZhou
 */
@Data
public class PagingUtil implements Serializable {

    private Integer size, page, pages, total;
    private List<String> numbers;

    /**
     * Specify the current page number and the size per page
     *
     * @param page the current page number, default 1, String for controller...
     * @param size the size per page, default 5, String for controller...
     */
    public PagingUtil(String page, String size) {
        try {
            int realPage = Integer.parseInt(page);
            int realSize = Integer.parseInt(size);
            if (realPage > 0 && realSize > 0) {
                this.page = realPage;
                this.size = realSize;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public PagingUtil() {
        this("1", "5");
    }

    /**
     * build suffix of mysql's paging SQL statment
     *
     * @return suffix of mysql's paging SQL statment
     */
    public String getLimitSuffix() {
        return " LIMIT " + ((this.page - 1) * size) + ", " + this.size;
    }

    /**
     * set number of total entries and calculate pages
     *
     * @param total number of total entries
     */
    public void buildTotalAndPages(int total) {
        this.total = total;
        double size = this.size;
        this.pages = (int) Math.ceil(total / size);
    }

    /**
     * build a paging list of numbers
     */
    public void buildNumbers() {

        numbers = new ArrayList<>();

        // constLength: when pages >= 2, ["1"], ["2"] is requried
        // varLength: uncertain numbers in page list must <= 5
        // maxLength: numbers in page list must <= 7
        int constLength = 2, varLength = 5, maxLength = 7;

        // ["1"] must exist
        numbers.add("1");

        if (pages > 1) {

            // when pages > 1, ["2"] must exist
            numbers.add("2");

            if (page <= varLength) {

                // add number after ["2"], <=pages and <=5
                for (int i = constLength; i < maxLength && i < pages; i++) {
                    numbers.add(i + 1 + "");
                }

                // when pages > 7, add ["..."]
                if (pages > maxLength) {
                    numbers.add("...");
                }
            } else {
                // when page > 5, the first ["..."] must exist
                numbers.add("...");

                // add two number before current page
                // add two number after current page
                // <= pages
                for (int i = -constLength; i <= constLength && page + i <= pages; i++) {
                    numbers.add(page + i + "");
                }

                // add second ["..."] when pages > 7 and at least two more
                if (pages > maxLength && page < pages - constLength) {
                    numbers.add("...");
                }
            }
        }
    }
}