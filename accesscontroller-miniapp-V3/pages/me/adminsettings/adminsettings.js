var utils = require('../../../utils/util.js')
// pages/me/adminsettings/adminsettings.js
//获取应用实例
const app = getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {
    roomArray: [],
    roomIndex: 0
  },
  
  bindPickerChange: function (e) {
    console.log('picker发送选择改变，携带值为', e.detail.value)
    this.setData({
      roomIndex: e.detail.value
    })
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var that = this
    wx.request({
      url: app.globalData.serverUrl + '/ac/wx/listRooms',
      data: {
        openId: wx.getStorageSync('openId')
      },
      success: function (res) {
        if(res.data.oList){
          //console.log(res.data)
          var roomArray_temp = [];
          for (var i = 0; i < res.data.oList.length; i++) {
            roomArray_temp.push({ "id": res.data.oList[i].id, "text": res.data.oList[i].name });
          }
          that.setData({
            roomArray: roomArray_temp
          })
        }
      }
    })
  },

  bindOpenGuard(){
    this.setGuard('open')
  },

  bindReset(){
    this.setGuard('reset')
  },

  setGuard(_sort){
    var that = this

    var _openId = wx.getStorageSync('openId')
    var _orgId = that.data.roomArray[that.data.roomIndex].id
    utils.showLoading();
    wx.request({
      url: app.globalData.serverUrl + '/ac/wx/setGuard',
      data: {
        openId: _openId,
        orgId: _orgId,
        sort: _sort
      },
      success: function (res) {
        if (res.data.success) {
          console.log(res.data.message)
          utils.cancelLoading();
        }
      },
      fail: function () {
        utils.cancelLoading();
      }
    })
  }
})