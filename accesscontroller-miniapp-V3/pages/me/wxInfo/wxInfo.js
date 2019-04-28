const app = getApp();

Page({
  data: {
    userInfo: null
  },

  onLoad: function (options)  {
    //console.log(app.globalData.userInfo)
    this.setData({
      userInfo: app.globalData.userInfoForWeChat
    })
  },

  back:function(){
    wx.navigateBack({
      delta: 1
    })
  }
});
