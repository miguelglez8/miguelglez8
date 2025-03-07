package com.preving.liferay.portlet.timesheet.report.web.portlet.command;


import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCResourceCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.preving.liferay.portlet.calendar.manager.model.UserData;
import com.preving.liferay.portlet.calendar.manager.model.WorkCenters;
import com.preving.liferay.portlet.calendar.manager.service.UserDataLocalServiceUtil;
import com.preving.liferay.portlet.calendar.manager.service.WorkCentersLocalServiceUtil;
import com.preving.liferay.portlet.timesheet.report.web.bean.PrevingSign;
import com.preving.liferay.portlet.timesheet.report.web.constants.TimesheetReportPortletKeys;
import com.preving.liferay.portlet.timesheet.report.web.portlet.TimesheetReportPortlet;
import com.preving.liferay.portlet.timesheet.report.web.util.PrevingSignUtil;
import jdk.nashorn.internal.ir.RuntimeNode;
import org.osgi.service.component.annotations.Component;

import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Component(
        immediate=true,
        property= {
                "javax.portlet.name=" + TimesheetReportPortletKeys.TIMESHEETREPORT ,
                "mvc.command.name=getTimeSheets"
        },
        service = MVCResourceCommand.class
)
public class GetTimeSheets extends BaseMVCResourceCommand {
    private static Log _log = LogFactoryUtil.getLog(GetTimeSheets.class);

    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    @Override
    protected void doServeResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse) throws Exception {

        ThemeDisplay themeDisplay = (ThemeDisplay) resourceRequest.getAttribute(WebKeys.THEME_DISPLAY);

        /*String companyId = ParamUtil.getString(resourceRequest,"companyId", StringPool.BLANK);
        String groupId = ParamUtil.getString(resourceRequest,"companyId", StringPool.BLANK);*/

        HttpServletRequest request =  PortalUtil.getOriginalServletRequest(PortalUtil.getHttpServletRequest(resourceRequest));


        String startDate2 = request.getParameter("startDate");

        String jobtitle = ParamUtil.getString(request,"jobtitle", StringPool.BLANK);
        String startDate = ParamUtil.getString(request,"startDate", StringPool.BLANK);
        String endDate = ParamUtil.getString(request,"endDate", StringPool.BLANK);
        String workCenter = ParamUtil.getString(request,"workCenter", "0");

        List<Long> arrWorkCentersId = new ArrayList<Long>();
        if(!workCenter.equals("0")){
            String[] arr = workCenter.split(",");
            for(String c : arr){
                arrWorkCentersId.add(Long.valueOf(c));
            }
        }else{
            arrWorkCentersId.add(0L);
        }

        Date stDate = null;
        Date enDate = null;

        try {
            if(!Validator.isBlank(startDate)){
                stDate = dateFormat.parse(startDate);
            }else{
                Calendar calStart = Calendar.getInstance();
                calStart.set(Calendar.DAY_OF_MONTH, 1);
                stDate = calStart.getTime();
            }

            if(!Validator.isBlank(endDate)){
                enDate = dateFormat.parse(endDate);
            }else{
                Calendar calEnd = Calendar.getInstance();
                enDate = calEnd.getTime();
            }
        } catch (ParseException e) {
            _log.error(e);
        }

        List<PrevingSign> signsBetwenDates = PrevingSignUtil.getSignsBetwenDates(themeDisplay.getCompanyId(), themeDisplay.getScopeGroupId(),
                                            jobtitle, stDate, enDate,arrWorkCentersId);

        List<String> jobTitlesFromCompany = UserDataLocalServiceUtil.getJobTitlesFromCompany(themeDisplay.getCompanyId(), themeDisplay.getScopeGroupId());

        JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
        JSONArray jsonArrayItems = JSONFactoryUtil.createJSONArray();
        JSONArray jsonArraySigns = null;
        JSONObject json = null;

        UserData userData = null;
        String strWorkCenter = "";
        WorkCenters wk = null;
        long auxUserId = 0L;

        _log.info("signsBetwenDates.size() : " + signsBetwenDates.size());

        for(PrevingSign sign : signsBetwenDates){
            jsonArraySigns = JSONFactoryUtil.createJSONArray();
            json = JSONFactoryUtil.createJSONObject();

            json.put("id", sign.getUserId() + "-" + dateFormat.format(sign.getDate())); //mantener forma antigua
            json.put("userId",sign.getUserId());
            json.put("name", sign.getUserName());

            if(auxUserId != sign.getUserId()){
                userData = UserDataLocalServiceUtil.getUserDataByGroupIdAndUserId(sign.getGroupId(), sign.getUserId());
                strWorkCenter = " ";
                if (Validator.isNotNull(userData)){
                    if(Validator.isNotNull(userData.getWorkCenterId()) && userData.getWorkCenterId() != 0){ //  han editado y asociado el usuario por id
                        wk = WorkCentersLocalServiceUtil.getWorkCenters(userData.getWorkCenterId());
                        if (Validator.isNotNull(wk)) {
                            strWorkCenter = wk.getName();
                        }
                    }else if(Validator.isNotNull(userData.getWorkCenter())){ // lo informarón por txt
                        strWorkCenter = userData.getWorkCenter();
                    }
                }
                auxUserId = sign.getUserId();
            }

            json.put("workCenter", strWorkCenter);
            json.put("job", sign.getJobTitle());

            json.put("date", dateFormat.format(sign.getDate()));
            json.put("dateLong", sign.getDate().getTime());

            json.put("startTime", sign.getStartTime());
            json.put("endTime", sign.getEndTime());

            json.put("totalTime", sign.getTotalTime());
            json.put("totalExtraTime", sign.getTotalExtraTime());
            json.put("rest", sign.getRest(themeDisplay.getLocale()));

            json.put("observations", sign.getObservations());
            json.put("observationsAdmin", sign.getObservationsAdmin());


            json.put("signs", jsonArraySigns);
            jsonArrayItems.put(json);
        }

        jsonObject.put("data", jsonArrayItems);
        resourceResponse.setContentType("application/json");
        resourceResponse.setCharacterEncoding("UTF-8");
        resourceResponse.getWriter().write(jsonObject.toString());

    }
}
