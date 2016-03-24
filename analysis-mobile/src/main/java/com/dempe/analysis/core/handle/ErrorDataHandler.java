package com.dempe.analysis.core.handle;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dempe.analysis.core.R;
import com.dempe.analysis.core.store.CountStoreMap;
import com.dempe.analysis.core.utils.DateUtil;

/**
 * @author : Dempe
 * @version 1.0 date : 2014/11/26
 */
public class ErrorDataHandler {

    public static final int LEN = 5;

    public void analysis(String basekey, String sysver, String networkType, JSONArray errorData, CountStoreMap storeMap) {
        if (errorData == null) {
            return;
        }
        JSONObject error;
        String createDate;
        String errorLog;
        String stackTrace;
        for (int i = 0, size = errorData.size(); i < size; i++) {
            error = errorData.getJSONObject(i);
            createDate = DateUtil.format(error.getString(R.ERROR_DATA_CREATE_DATE));
            errorLog = error.getString(R.ERROR_DATA_ERROR_LOG);
            stackTrace = error.getString(R.ERROR_DATA_STACK_TRACE);

            int errorLogLen = errorLog.length();
            int stackTraceLen = stackTrace.length();
            if (errorLogLen > LEN) {
                errorLog = errorLog.substring(0, LEN);
            }
            if (stackTraceLen > LEN) {
                stackTrace = stackTrace.substring(0, LEN);
            }
            storeMap.incr(R.COUNT, new StringBuffer(R.ERROR_INFO).append(R.DOLLAR_SPLIT).append(createDate).
                    append(basekey).append(R.KEY_SPACE).append(networkType).append(R.KEY_SPACE).append(sysver).
                    append(R.KEY_SPACE).append(errorLog).append(R.KEY_SPACE).append(stackTrace).toString());
        }
    }
}
