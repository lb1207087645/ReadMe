import com.graduation.android.readme.base.mvp.BaseView
import com.graduation.android.readme.base.mvp.IPresenter
import com.graduation.android.readme.bean.User

class SettingContract {
    interface View : BaseView {
        /**
         * 修改密码成功
         */
        fun modifyPwdSuccess()

        fun modifyPwdFailure(errMsg: String)


        fun modifyNickNameSuccess()

        fun modifynickNameFailure(errMsg: String)


        fun updateUserSexSuccess()

        fun updateUserSexFailure(mssage: String)

        fun feedbackSuccess()

        fun mfeedbackFailure(errMsg: String)
    }

    /**
     */
    interface Presenter : IPresenter<View> {
        fun modifyPwd(oldPwd: String, newPwd: String)
        fun updateInfo(nickName: String)//修改昵称

        fun updateUserSex(sexMsg: Int)

        fun feedback(feedBack: String)//反馈

    }
}
