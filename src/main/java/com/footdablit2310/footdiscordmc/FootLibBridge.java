package com.footdablit2310.footdiscordmc;

import com.footdablit2310.footlib.api.common.rcc_api.RCCAPI;
import com.footdablit2310.footlib.api.common.rcc_api.RCCCommand;
import com.footdablit2310.footlib.api.common.rcc_api.RCCResponse;
import com.footdablit2310.footlib.impl.rcc_impl.RCCImpl;

public class FootLibBridge {
    public static RCCResponse handleIncoming(String payload){
        RCCCommand rccCommand=FootLibUtil.convertToRCCCommand(payload);
        RCCImpl rcc = new RCCImpl();
        return rcc.execute(rccCommand);
    }
}
