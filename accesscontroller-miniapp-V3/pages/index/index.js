var utils = require('../../utils/util.js')
var wxApi = require('../../utils/wxApi')
var genqrcode = require('../../plugins/genqrcode.js')
//index.js
//获取应用实例

const app = getApp()

Page({
  data: {
    message: {
      color: "red",
      content: "您还未绑定基础信息，点击绑定！"
    },
    remainingSeconds: 0,
    tipText: "下拉获取二维码...",
    tipTextColor: "red",
    interval:null
  },
  onLoad:function(){
    app.userInfoForACReadyCallback = () => {
      if (app.globalData.userInfoForAC) {
        this.setData({
          message: { color: "green", content: "已绑定基础信息！" }
        })
      }
      this.genQrCode();
    }

  },
  // onShow: function () {
  //   if (app.globalData.userInfoForAC) {
  //     this.setData({
  //       message: { color: "green", content: "已绑定基础信息！" }
  //     })
  //     this.genQrCode();
  //   } 
  // },
  /**
   * 生成二维码
   */
  genQrCode: function (e) {
    if (!wx.getStorageSync('openId') || !app.globalData.userInfoForAC){
      return;
    }

    utils.showLoading();
    wx.showNavigationBarLoading();

    var _uuidKey = utils.v1()
    var _openId = wx.getStorageSync('openId')

    var url = app.globalData.serverUrl + '/ac/wx/setQrSession';
    var data = {
      uuidKey: _uuidKey,
      openId: _openId
    };
    wxApi.getRequest(url, data).then(res => {
      console.log("【 " + new Date() +" 】设置服务端session:" + res.data.success);
      //调用插件中的draw方法，绘制二维码图片
      genqrcode.qrApi.draw(_uuidKey, "mycanvas", 300, 300);
      this.setData({
        remainingSeconds: 100,
        tipText: "请对准扫描器",
        tipTextColor: "green"
      });
      clearInterval(this.data.interval);
      //开始计时
      this.countDown();
    }, res => {
      utils.showModal("提示", "服务端连接异常！", false, "确定");
    }).finally(() => {
      // 隐藏导航栏加载框  
      wx.hideNavigationBarLoading();
      utils.cancelLoading();
    });
  },

  countDown: function countdown() {

    var that=this
    var _interval = setInterval(function () {
      that.setData({
        remainingSeconds: that.data.remainingSeconds - 1
      });
      if (that.data.remainingSeconds == 0){
        that.setData({
          remainingSeconds: 0,
          tipText: "已过期，下拉刷新",
          tipTextColor: "red"
        });
        clearInterval(_interval);
      }
    }, 1000)

    this.setData({
      interval: _interval
    })
  },

  onPullDownRefresh: function () {
    if (app.globalData.userInfoForAC) {
      this.setData({
        message: { color: "green", content: "已绑定基础信息！" }
      })
      this.genQrCode();
    }
    else {
      app.setSystemInfo();
    }
    wx.stopPullDownRefresh();
  }
})
