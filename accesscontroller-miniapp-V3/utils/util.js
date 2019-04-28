const formatTime = date => {
  const year = date.getFullYear()
  const month = date.getMonth() + 1
  const day = date.getDate()
  const hour = date.getHours()
  const minute = date.getMinutes()
  const second = date.getSeconds()

  return [year, month, day].map(formatNumber).join('/') + ' ' + [hour, minute, second].map(formatNumber).join(':')
}

const formatNumber = n => {
  n = n.toString()
  return n[1] ? n : '0' + n
}

function showLoading() {
  wx.showToast({
    title: '加载中',
    icon: 'loading',
    duration: 10000
  });
}

function cancelLoading() {
  wx.hideToast();
}


function showModal(title,content,showCancel,confirmText){
   wx.showModal({
     title: title,
     content: content,
     showCancel: showCancel,
     cancelText: '',
     cancelColor: '',
     confirmText: confirmText,
     confirmColor: '',
     success: function(res) {},
     fail: function(res) {},
     complete: function(res) {},
   })
}

function v1() {
  var s = [];
  var hexDigits = "0123456789abcdef";
  for (var i = 0; i < 36; i++) {
    s[i] = hexDigits.substr(Math.floor(Math.random() * 0x10), 1);
  }
  s[14] = "4";  // bits 12-15 of the time_hi_and_version field to 0010
  s[19] = hexDigits.substr((s[19] & 0x3) | 0x8, 1);  // bits 6-7 of the clock_seq_hi_and_reserved to 01
  s[8] = s[13] = s[18] = s[23] = "-";

  var uuid = s.join("");
  return uuid;
}  

module.exports = {
  formatTime: formatTime,
  showLoading: showLoading,
  cancelLoading: cancelLoading,
  v1: v1,
  showModal:showModal
}


