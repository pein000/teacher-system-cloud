//logs.js
var util = require('../../utils/util.js')
Page({
  data: {
    files: [],
    paper:{
      name:"adfasdfsd",
      description:"sdgggasdffsdf",
      urls: []
    }
  },
  chooseImage: function (e) {
    var that = this;
    wx.chooseImage({
      sizeType: ['original','compressed'], // 可以指定是原图'original',还是压缩图，默认二者都有
      sourceType: ['album', 'camera'], // 可以指定来源是相册还是相机，默认二者都有
      compress:{
        quantity: .9
      },
      
      success: function (res) {
        // 返回选定照片的本地文件路径列表，tempFilePath可以作为img标签的src属性显示图片
        var tempFilePaths = res.tempFilePaths
        
        wx.uploadFile({
          url: getApp().globalData.serverPath+'/api/upload', //接口地址
          filePath: tempFilePaths[0],
          name: 'file',
          formData: {
            'phone': '13564203541'
          },
          success: function (response) {
            var result = JSON.parse(response.data)
            if(result.success){//返回成功
              if (result.data != null && result.data != ''){
                that.data.paper.urls.push(result.data)
                //显示预览
                that.setData({
                  files: that.data.files.concat(tempFilePaths)
                });
              }
            }
            
          },
          fail:  function(error,e){
            wx.showToast({
              title: '上传图片识别'+error+":"+e,
              icon: '',
              image: '',
              duration: 1000,
              mask: true,
              success: function(res) {},
              fail: function(res) {},
              complete: function(res) {},
            })
          }
        })
      }
    })
  },
  previewImage: function (e) {
    wx.previewImage({
      current: e.currentTarget.id, // 当前显示图片的http链接
      urls: this.data.files // 需要预览的图片http链接列表
    })
  },
  savePaper: function(e) {
    var that = this;
    wx.request({
      url: getApp().globalData.serverPath + '/api/save_paper', //仅为示例，并非真实的接口地址
      method:'POST',
      data: that.data.paper,
      header: {
        'content-type': 'application/x-www-form-urlencoded'
      },
      success: function (response) {
        var result = response.data
        if (result.success) {//返回成功
          wx.redirectTo({
            url: '/pages/identify_success/success'
          })
        }
      }
    })
  }

})
