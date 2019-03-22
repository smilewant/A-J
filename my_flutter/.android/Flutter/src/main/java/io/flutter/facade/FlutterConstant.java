package io.flutter.facade;


public class FlutterConstant {
    /*
     * 跳转的方法
     */
    public final static String METHOD_JUMP_TO_ACTIVITY = "jumpToPage";//需要跳转到Activity的方法名
    public final static String METHOD_BACK = "backAction";
    public final static String METHOD_DIALOG_SHOW = "startActivityAnimatingAction";
    public final static String METHOD_DIALOG_DISMISS = "stopActivityAnimatingAction";
    public final static String METHOD_TOAST_SHOW = "showToast";


    /*
     * 跳转到Activity的名字
     */
    public final static String ACTIVITY_SPECIAL_DETAIL = "gotoGrouponDetailPage";
    public final static String ACTIVITY_WEBVIEW = "gotoWebviewPage";
    public final static String ACTIVITY_HOLIDAY_OUT_SET_CTIY= "gotoSelectCityPage";

    /*
     * 调用Flutter里的方法
     */

    public final static String METHOD_F_SELECT_CITY = "_selectCityCallback";
}
