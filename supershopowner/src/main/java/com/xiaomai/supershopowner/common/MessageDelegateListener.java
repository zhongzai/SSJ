package com.xiaomai.supershopowner.common;

import java.io.Serializable;  

public interface MessageDelegateListener {  
    void handleMessage(Serializable message);  
}  
