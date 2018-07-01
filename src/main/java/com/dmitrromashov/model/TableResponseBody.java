package com.dmitrromashov.model;

import java.util.List;

public class TableResponseBody {
    private List<Integer> ids;
    private String[] statuses;

    public List<Integer> getIds() {
        return ids;
    }

    public void setIds(List<Integer> ids) {
        this.ids = ids;
    }

    public String[] getStatuses() {
        return statuses;
    }

    public void setStatuses(String[] statuses) {
        this.statuses = statuses;
    }
}
