package com.hb.umeng.analytics;

/**
 *
 * 自定义事件枚举
 * Created by txl on 2017/5/28 0028.
 */
public enum EventEnum {
    homepagesearchbutton("homepagesearchbutton"),
    homepagehospitalregisterbutton("homepagehospitalregisterbutton"),
    homepagedepartmentregisterbutton("homepagedepartmentregisterbutton"),
    homepagephoneregisterbutton("homepagephoneregisterbutton"),
    homepagecommondepartmentbutton("homepagecommondepartmentbutton"),
    homepagenewsbutton("homepagenewsbutton"),
    homepagehealthbaikebutton("homepagehealthbaikebutton"),
    homepagedoctoraccompanybutton("homepagedoctoraccompanybutton"),
    homepagehostitalescortbutton("homepagehostitalescortbutton"),
    homepagefamilyescortbutton("homepagefamilyescortbutton"),
    homepagehealthcommunicationbutton("homepagehealthcommunicationbutton"),
    homepagehealthinquirybutton("homepagehealthinquirybutton"),
    homepagehealthnavigationbutton("homepagehealthnavigationbutton"),
    homepagedoctorservicebutton("homepagedoctorservicebutton"),
    homepagehospitalindexbutton("homepagehospitalindexbutton"),
    homebutton("homebutton"),
    mydoctorpageitemlistclick("mydoctorpageitemlistclick"),
    mydoctorbutton("mydoctorbutton"),
    minepageaccountheadbutton("minepageaccountheadbutton"),
    minepageaccountbutton("minepageaccountbutton"),
    minepagepatientbutton("minepagepatientbutton"),
    minepagepatientcardbutton("minepagepatientcardbutton"),
    minepagemyreservationregisterbutton("minepagemyreservationregisterbutton"),
    minepagemyorderbutton("minepagemyorderbutton"),
    minepagefeedbackbutton("minepagefeedbackbutton"),
    minepagesettingbutton("minepagesettingbutton"),
    minepageappscorebutton("minepageappscorebutton"),
    minepagenoticebutton("minepagenoticebutton"),
    minebutton("minebutton"),
    hospitallistpageitemlistclick("hospitallistpageitemlistclick"),
    hospitallistpageareachoosebutton("hospitallistpageareachoosebutton"),
    hospitaldepartmentlistpagelistclick("hospitaldepartmentlistpagelistclick"),
    hospitaldepardoctorlistpagelistclick("hospitaldepardoctorlistpagelistclick"),
    secondstandarddepartmentlistclick("secondstandarddepartmentlistclick"),
    standarddepartmentdoctorlistclick("standarddepartmentdoctorlistclick"),
    standarddepartmentdoctorlistpageareachooseclick("standarddepartmentdoctorlistpageareachooseclick"),
    phoneregistercmccbutton("phoneregistercmccbutton"),
    phoneregistercuccbutton("phoneregistercuccbutton"),
    phoneregisterctccbutton("phoneregisterctccbutton"),
    doctorpagedoctorheadbutton("doctorpagedoctorheadbutton"),
    doctorpagedoctorintroductionbutton("doctorpagedoctorintroductionbutton"),
    doctorpagedoctorfollowbutton("doctorpagedoctorfollowbutton"),
    doctorpagedoctorschedulenoticebutton("doctorpagedoctorschedulenoticebutton"),
    doctorpagedoctormeritflexiblebutton("doctorpagedoctormeritflexiblebutton"),
    doctorpagedoctorconsultbutton("doctorpagedoctorconsultbutton"),
    doctorpagedoctorsalonbutton("doctorpagedoctorsalonbutton"),
    doctorpagedoctorfollowgotobutton("doctorpagedoctorfollowgotobutton"),
    doctorpagedoctorscheduleareaclick("doctorpagedoctorscheduleareaclick"),
    doctorpagedoctorregistertimechooseclick("doctorpagedoctorregistertimechooseclick"),
    doctorpagedoctorregisterpatientchoosebutton("doctorpagedoctorregisterpatientchoosebutton"),
    doctorpagedoctorregisterpatientcardchoosebutton("doctorpagedoctorregisterpatientcardchoosebutton"),
    doctorpagedoctorregisterpaymentchoosebutton("doctorpagedoctorregisterpaymentchoosebutton"),
    doctorpagedoctorregistersubmitbutton("doctorpagedoctorregistersubmitbutton"),
    doctorpagedoctorregisterpaymentconfirmbutton("doctorpagedoctorregisterpaymentconfirmbutton"),
    doctorpagedoctorregistersuccessregisterdetailbutton("doctorpagedoctorregistersuccessregisterdetailbutton"),
    minepagemyreservationregisterlistcancelbutton("minepagemyreservationregisterlistcancelbutton"),
    minepagemyreservationregisterlistpaybutton("minepagemyreservationregisterlistpaybutton"),
    minepagemyreservationregisterlistclick("minepagemyreservationregisterlistclick"),
    minepagemyreservationregisterdetailcancelbutton("minepagemyreservationregisterdetailcancelbutton"),
    minepagemyreservationregisterdetailpaybutton("minepagemyreservationregisterdetailpaybutton"),
    minepagemyreservationregisterdetaildoctorpagebutton("minepagemyreservationregisterdetaildoctorpagebutton"),
    loginpageloginbutton("loginpageloginbutton"),
    loginpageseekpasswordbutton("loginpageseekpasswordbutton"),
    loginpagequickloginbutton("loginpagequickloginbutton"),
    loginpagequickloginphonenumberdonenextbutton("loginpagequickloginphonenumberdonenextbutton"),
    loginpagequickloginidentifyingcodedonenextbutton("loginpagequickloginidentifyingcodedonenextbutton"),
    loginpagequickloginsubmitbutton("loginpagequickloginsubmitbutton"),
    loginpagebackbutton("loginpagebackbutton");




    private String id;

    private EventEnum(String id){
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
