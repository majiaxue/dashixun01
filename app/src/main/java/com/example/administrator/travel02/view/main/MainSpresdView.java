package com.example.administrator.travel02.view.main;

import com.example.administrator.travel02.base.BaseMvpView;
import com.example.administrator.travel02.bean.MainSpreadBean;

public interface MainSpresdView extends BaseMvpView {

    void onUiFile(String msg);

    void upDataUi(MainSpreadBean bean);

    void onSuccess(String bean);

    void onFail(String msg);
}
