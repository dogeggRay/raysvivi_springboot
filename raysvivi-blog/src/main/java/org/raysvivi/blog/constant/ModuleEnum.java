package org.raysvivi.blog.constant;

public enum ModuleEnum {

    SAY_SAY("saysay", "说说"),
    ABOUT("about", "关于"),
    BLOG_DETAIL("博客详情", "说说"),
    FRIENDLY_LINK("friendlyLink", "友情链接"),
    COMMENT("comment", "留言板");


    private final String id;
    private final String value;

    ModuleEnum(String id, String value) {
        this.id = id;
        this.value = value;
    }

    public String getId() {
        return this.id;
    }

    public String getValue() {
        return this.value;
    }
}
