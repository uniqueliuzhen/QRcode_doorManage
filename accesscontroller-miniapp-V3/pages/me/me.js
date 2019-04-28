var utils = require('../../utils/util.js')
const app = getApp();

Page({
  data: {
    userInfo:null,
    isAdmin:false
  },

  onShow:function(){
    if (app.globalData.userInfoForAC) {
      this.setData({
        userInfo: app.globalData.userInfoForWeChat,
        isAdmin: app.globalData.userInfoForAC.isAudit == 9 ? true : false
      })
    } else {
      this.setData({
        userInfo: app.globalData.userInfoForWeChat
      })
    }
  },

  onPullDownRefresh: function () {

    this.onShow();
    wx.stopPullDownRefresh();
  },

  goBindInfo() {
    wx.navigateTo({ 
      url: 'bindInfo/bindInfo'
    });
  },

  goHelp() {
    wx.navigateTo({
      url: 'help/help'
    });
  },

  goAdminSettings(){
    wx.navigateTo({
      url: 'adminsettings/adminsettings'
    });
  },

  goAudit() {
    wx.navigateTo({
      url: 'audit/audit'
    });
  }
});
