// success.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
  
  },

  switchMe: function(e){
    wx.switchTab({
      url: '/pages/me/index',
    })
  }
})