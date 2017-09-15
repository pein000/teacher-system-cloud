//index.js
//获取应用实例
var app = getApp()
var sliderWidth = 0; // 需要设置slider的宽度，用于计算中间位置
Page({
  data: {
    motto: 'Hello World',
    tabs: ["推荐", "少儿", "搞笑"],
    activeIndex: 0,
    sliderOffset: 0,
    sliderLeft: 0,
    userInfo: {}
  },
  //事件处理函数
  bindViewTap: function() {
    wx.navigateTo({
      url: '../logs/logs'
    })
  },
  onLoad: function () {
    console.log('onLoad')
    var that = this;
    //调用应用实例的方法获取全局数据
    app.getUserInfo(function(userInfo){
      //更新数据
      that.setData({
        userInfo:userInfo
      })
    });
    wx.getSystemInfo({
      success: function (res) {
        that.setData({
          // sliderLeft: (res.windowWidth / that.data.tabs.length - sliderWidth) / 2,
          sliderLeft: 0,
          // sliderOffset: res.windowWidth / that.data.tabs.length * that.data.activeIndex
          sliderOffset: 0
        });
      }
    });
  },
  tabClick: function (e) {
    this.setData({
      sliderOffset: e.currentTarget.offsetLeft,
      activeIndex: e.currentTarget.id
    });
  }, 
  onPullDownRefresh: function(e){
    console.log('下拉刷新.。。')
  }
});