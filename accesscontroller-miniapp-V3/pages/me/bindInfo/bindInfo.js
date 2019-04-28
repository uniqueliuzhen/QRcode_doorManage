var utils = require('../../../utils/util.js')

//获取应用实例
const app = getApp()
// pages/me/bindInfo/bindInfo.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    userInfo: {},
    stuNumber: null,
    realName: null,
    stuInput: true,
    nameInput: true,
    btnState: false,
    btnInfo: "绑定"
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    if (app.globalData.userInfoForAC){
      this.setData({
        userInfo: app.globalData.userInfoForWeChat,
        isAdmin: app.globalData.userInfoForAC.isAudit == 9 ? true : false,
        stuNumber: app.globalData.userInfoForAC.stuNumber,
        realName: app.globalData.userInfoForAC.realName
      })
    }else{
      this.setData({
        userInfo: app.globalData.userInfoForWeChat
      })
    }
  
  },


  numberInput: function (e) {
    this.setData({
      stuNumber: e.detail.value
    })
  },
  nameInput: function (e) {
    this.setData({
      realName: e.detail.value
    })
  },
  bindinfo: function () {
    if (this.data.stuNumber == null || this.data.realName == null) {
      wx.showModal({
        title: "提示",
        content: "学号或姓名不可为空！",
        showCancel: false,
        confirmText: "确定"
      })
      return;
    }
    utils.showLoading();
    wx.request({
      url: app.globalData.serverUrl + '/ac/wx/bindinfo',
      data: {
        stuNumber: this.data.stuNumber,
        openId: wx.getStorageSync('openId'),
        realName: this.data.realName
      },
      header: {
        'content-type': 'application/x-www-form-urlencoded;charset=utf-8',
      },
      success: function (res) {
        utils.cancelLoading();
        if (res.data.success) {
          app.globalData.userInfoForAC=true;
          wx.showModal({
            title: "提示",
            content: res.data.message,
            showCancel: false,
            confirmText: "确定",
            success: function (res) {
              if (res.confirm) {
                wx.navigateBack({
                  delta: 1
                })
              }  
            }
          })
        }
      }
    })
  }
})