package com.qgstudio.anywork.data;


public enum StatEnum2 {

    /**
     * 通用板块
     */
    VALCODE_WRONG(0,"验证码错误"),
    DEFAULT_WRONG(-1,"其他错误"),
    /**
     * 邮箱验证板块
     * */
    SEND_SUCCESS(101,"邮件发送成功"),
    SEND_FORMATTER_FAULT(102,"邮箱格式错误"),
    SEND_FAULT(103,"邮件发送失败"),

    /**
     * 注册板块
     */
    REGISTER_SUCESS(111,"注册成功"),
    REGISTER_EMPTY_USER(112,"空用户对象"),
    REGISTER_FAMMTER_FAULT(113,"注册信息格式错误"),
    REGISTER_CIPHERTEXT_MISMATCH(114,"密文信息不匹配"),
    REGISTER_ALREADY_EXIST(115,"已存在的用户"),

    /**
     * 登录板块
     */
    LOGIN_SUCCESS(121,"登录成功"),
    LOGIN_NOT_EXIT_USER(122,"不存在的用户"),
    LOGIN_USER_MISMATCH(123,"用户名或密码错误"),


    /**
     *
     * zggdczfr
     *
     */

    /**
     * 权限通用模块
     * 微信模块
     */
    WITHOUT_POWER(191, "用户没有相应的处理权限"),
    ERROR_DELETE(192, "不能删除负责人或管理员"),
    WEIXIN_ORGANIZATION(193, "微信用户获取组织列表成功"),
    WEIXIN_INFORM(194, "微信客户端获取公告"),
    WETXIN_QUESTION(195, "微信客户端获取作业"),
    WEIXIN_REQUEST_ERROR(196, "微信客户端获取信息失败"),

    /**
     * 组织申请模块
     */
    APPLY_SEND_SUCCESS(151, "组织申请发送成功"),
    APPLY_SEND_FAIL(152, "组织申请发送失败"),
    APPLY_HANDLE_SUCCESS(154, "处理申请成功"),
    APPLY_HANDLE_FAIL(155, "处理申请失败"),
    APPLY_NOHANDLR_ALL(156, "获得所有未处理的组织申请"),

    /**
     * 评论和回复模块
     */
    COMMENT_ALL(161, "获取所有的评论和回复"),
    COMMENT_ANNOUNCE_SUCCESS(162, "发表评论成功"),
    RECOMMENT_ANNOUNCE_SUCCESS(163, "回复评论成功"),
    COMMENT_ANNOUNCE_FAIL(164, "发表失败"),

    /**
     * 组织关系处理模块
     */
    RELATION_DELETE_SUCCESS(171, "删除用户成功"),
    RELATION_DELETE_FAIL(172, "删除用户失败"),
    RELATION_ALL(173, "获取所有未处理申请"),
    RELATION_UPDATE_SUCCESS(174, "设置权限成功"),
    RELATION_UPDATE_FAIL(175, "设置权限失败"),
    RELATION_ALL_ORGAN(176, "获取用户组织列表"),

    /**
     * 组织模块
     */
/*    ORGAN_CREATE_SUCCESS(181, "创建组织成功"),
    ORGAN_CREATE_FAIL(182, "创建组织失败"),*/
/*    ORGAN_SEARCH_ID(183, "通过组织id来查找组织"),
    ORGAN_SEARCH_NAME(184, "通过组织名来查找组织"),*/

    ALL(999,"test");


    private  int state;
    private  String stateInfo;

    StatEnum2(int state, String stateInfo) {
        this.state = state;
        this.stateInfo = stateInfo;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public void setStateInfo(String stateInfo) {
        this.stateInfo = stateInfo;
    }
    public  static StatEnum2 statOf(int index) {
        for (StatEnum2 state : values()) {
            if (state.getState() == index) {
                return  state;
            }
        }
        return  null;
    }
}
