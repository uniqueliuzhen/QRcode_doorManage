var utils = require('../../../utils/util.js')
var wxApi = require('../../../utils/wxApi.js')
var sliderWidth = 96;                             // 需要设置slider的宽度，用于计算中间位置

const app = getApp()

Page({

  data: {
    tabs: ["待审核", "已审核", "已拒绝"],
    activeIndex: 0,
    sliderOffset: 0,
    sliderLeft: 0,
    AuditList: [],
  },
  onLoad: function () {
    //设置tab宽度
    var wxGetSystemInfo = wxApi.wxGetSystemInfo();
    wxGetSystemInfo(res => {
      this.setData({
        sliderLeft: (res.windowWidth / this.data.tabs.length - sliderWidth),
        sliderOffset: res.windowWidth / this.data.tabs.length * this.data.activeIndex
      });
    });
    this.refreshAuditList();
  },

  //刷新资源列表
  refreshAuditList: function () {
    utils.showLoading();
    var url = app.globalData.serverUrl + '/ac/wx/loadauditlist';
    var data = {}
    wxApi.getRequest(url, data).then(res => {
      //console.log(res.data);
      this.setData({
        AuditList: res.data.rows
      })
      //关闭loading
      utils.cancelLoading();
    });
  },

  /**
   * 申请机房
   */
  applyHandler: function (e) {
    var that = this
    wx.showActionSheet({
      itemList: ['通过', '拒绝'],
      success: function (res) {

        if (!res.cancel) {
          var status = -1
          if (res.tapIndex == 0) {
            status = 1;
          } else {
            status = 2;
          }

          var url = app.globalData.serverUrl + '/ac/wx/setauditstatus';
          var data = {
            status: status,
            id: e.currentTarget.id
          }
          wxApi.getRequest(url, data).then(res => {
            console.log(res.data)
            that.refreshAuditList();
          });
        }
      }
    })
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {

  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {
    wx.stopPullDownRefresh();
    this.refreshAuditList();
  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {

  },

  tabClick: function (e) {
    this.setData({
      sliderOffset: e.currentTarget.offsetLeft,
      activeIndex: e.currentTarget.id
    });
  }
})