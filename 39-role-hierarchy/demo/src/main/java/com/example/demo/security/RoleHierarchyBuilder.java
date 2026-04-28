package com.example.demo.security;

public class RoleHierarchyBuilder {
    private final StringBuilder builder = new StringBuilder();
    public RoleHierarchyBuilder append(String uplineRole,String downlineRole) {
        builder.append(String.format("ROLE_%s > ROLE_%s\n", uplineRole, downlineRole));
        return this;
    }
    public String build() {
        return builder.toString();
    }
}
