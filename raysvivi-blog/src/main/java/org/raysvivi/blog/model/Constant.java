package org.raysvivi.blog.model;

public interface Constant {
    class Common {
        public static final Integer EFFECTIVE = 0;

        public static final String ROOT_INDEX = "-1";

        public static final String NONE_RELATIVE_ID = "-1";
    }

    class RedisConstant {
        public static final String FOOT_STEP_RECORD = "foot_step_record";

        public static final String PAGE_VIEW_NUMBER = "page_view_number";

        public static final Integer FOOTSTEP_DURATION = 24*60*60;

        public static final Integer PAGE_EXTENTION_DURATION = 30*60;
    }
}
