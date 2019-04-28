var utils = require('../../utils/util.js')
var wxApi = require('../../utils/wxApi.js')
var sliderWidth = 96; // 需要设置slider的宽度，用于计算中间位置
//获取应用实例
const app = getApp()
Page({
  data: {
    tabs: ["可用", "申请", "待审核"],
    activeIndex: 0,
    sliderOffset: 0,
    sliderLeft: 0,
    ResourceList: [],
  },
  onLoad: function () {
    //设置tab宽度
    var wxGetSystemInfo = wxApi.wxGetSystemInfo();
    wxGetSystemInfo(res=>{ 
        that.setData({
          sliderLeft: (res.windowWidth / that.data.tabs.length - sliderWidth) / 2,
          sliderOffset: res.windowWidth / that.data.tabs.length * that.data.activeIndex
        });
    });

    this.refreshResourceList();
  },

  //刷新资源列表
  refreshResourceList:function(){
    utils.showLoading();
    var url = app.globalData.serverUrl + '/ac/wx/listRooms';
    var data = { openId: wx.getStorageSync('openId') }
    wxApi.getRequest(url, data).then(res => {
      var temp = [];
      for (var i = 0; i < res.data.oList.length; i++) {
        var obj = {};
        obj.id = res.data.oList[i].id;
        obj.name = res.data.oList[i].name;
        obj.contact = res.data.oList[i].fax;
        obj.audit = 2;//可申请
        if (res.data.soList) {
          for (var j = 0; j < res.data.soList.length; j++) {
            if (res.data.oList[i].id == res.data.soList[j].orgId) {
              obj.isAudit = res.data.soList[j].isAudit;
              if (res.data.soList[j].isAudit == 0) {
                obj.audit = 0//审核中
              }
              else if (res.data.soList[j].isAudit == 1) {
                obj.audit = 1;//可用
              }
            }
          }
        }

        temp.push(obj);
      }
      this.setData({
        ResourceList: temp
      })
      //关闭loading
      utils.cancelLoading();
    });
  },
   /**
   * 申请机房
   */
  applyForAResource:function(e){
    var that = this
    wx.showModal({
      title: '提示',
      content: '确定申请该机房吗？',
      success: function (res) {
        if (res.confirm) {
          wx.request({
            url: app.globalData.serverUrl + '/ac/wx/askRoom',
            data: {
              orgId: e.currentTarget.id,
              openId: wx.getStorageSync("openId")
            },
            success: function (res) {
                that.refreshResourceList();
                wx.showModal({
                  title: "提示",
                  content: res.data.message,
                  showCancel: false,
                  confirmText: "确定"
                })
            }
          })
        } else if (res.cancel) {
           
        }
      }
    })
  },

  tabClick: function (e) {
    this.setData({
      sliderOffset: e.currentTarget.offsetLeft,
      activeIndex: e.currentTarget.id
    });
  },
  onPullDownRefresh: function () {
    wx.stopPullDownRefresh();
    this.refreshResourceList();
  },
});