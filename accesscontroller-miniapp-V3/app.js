var utils = require('utils/util.js')
var wxApi = require('utils/wxApi')
var genqrcode = require('plugins/genqrcode.js')
var app=getApp()
//app.js
App({
  onLaunch: function () {
    wx.getUserInfo({
      success: res => {
        // 可以将 res 发送给后台解码出 unionId 
        this.globalData.userInfoForWeChat = res.userInfo
        console.log("【 " + new Date() + " 】加载微信用户信息成功");
        this.setSystemInfo();
      }
    })
  },
  setSystemInfo:function(){
    utils.showLoading();
    var wxLogin = wxApi.wxLogin();
    //1.登陆
    wxLogin()
      .then(
      res => {
        console.log("【 " + new Date() + " 】微信用户登陆成功 ")
        var url = this.globalData.serverUrl + '/ac/wx/getStudentInfoByCode';
        var data = { code: res.code };
        return wxApi.getRequest(url, data);
      },
      res => {
        console.log("【 " + new Date() + " 】登陆失败 】")
      })
      .then(
      res => {
        if (typeof (res.data) == 'object') {
          console.log("【 " + new Date() + " 】获取门禁系统用户信息成功")
          wx.setStorageSync('openId', res.data.openid);
          this.globalData.userInfoForAC = res.data;
        }
        else if (res.data.length === 28) {
          console.log("【 " + new Date() + " 】门禁系统用户信息为空，获取OPENID")
          wx.setStorageSync('openId', res.data);
        }
        else {
          utils.showModal("提示", "服务端异常！", false, "确定");
        }

        this.globalData.openId = wx.getStorageSync("openId");
        console.log("【 " + new Date() + " 】获取微信用户openid:" + wx.getStorageSync("openId"))

        if (this.userInfoForACReadyCallback) {
          this.userInfoForACReadyCallback()
        }
      },
      () => {
        utils.showModal("提示", "服务端连接异常！", false, "确定");
      }).finally(res => {
        //关闭loading
        utils.cancelLoading();
      });
  },

  globalData: {
    userInfoForWeChat: null,//微信用户信息 
    userInfoForAC: null,    //门禁系统绑定信息
    openId: null,
    serverUrl: 'https://306studio.imnu.edu.cn/accesscontroller'
    //serverUrl: 'http://localhost:8080/accesscontroller'
  }
})