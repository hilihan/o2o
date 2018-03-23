$(function() {
	// 从URL里获取shopId参数的值
	var shopId = getQueryString('shopId');
	// 由于店铺注册和编辑使用的是同一个页面，
	// 该标识符用来标明本次是添加还是编辑操作
	var isEdit = shopId?true:false;
	// 用于店铺注册时候的店铺类别以及区域列表的初始化的URL
	var initUrl = '/o2o/shopadmin/getshopinitinfo';
	// 注册店铺的URL
	var registerShopUrl = '/o2o/shopadmin/registershop';
	// 编辑店铺前需要获取店铺信息，这里为获取当前店铺信息的URL
	var shopInfoUrl = '/o2o/shopadmin/getshopbyid?shopId=' + shopId;
	// 编辑店铺信息的URL
	var editShopUrl = '/o2o/shopadmin/modifyshop';
	
	// 判断是编辑操作还是注册操作
	if (!isEdit) {
		getShopInitInfo();
	} else {
		getShopInfo(shopId);
	}
	
	// 通过店铺Id获取店铺信息
	function getShopInfo(shopId) {
		$.getJSON(shopInfoUrl, function(data) {
			if (data.success) {
				// 若访问成功，则依据后台传递过来的店铺信息为表单元素赋值
				var shop = data.shop;
				$('#shop-name').val(shop.shopName);
				$('#shop-addr').val(shop.shopAddr);
				$('#shop-phone').val(shop.phone);
				$('#shop-desc').val(shop.shopDesc);
				// 给店铺类别选定原先的店铺类别值
				var shopCategory = '<option data-id="'
						+ shop.shopCategory.shopCategoryId + '" selected>'
						+ shop.shopCategory.shopCategoryName + '</option>';
				var tempAreaHtml = '';
				// 初始化区域列表
				data.areaList.map(function(item, index) {
					tempAreaHtml += '<option data-id="' + item.areaId + '">'
							+ item.areaName + '</option>';
				});
				$('#shop-category').html(shopCategory);
				// 不允许选择店铺类别
				$('#shop-category').attr('disabled', 'disabled');
				$('#shop-area').html(tempAreaHtml);
				// 给店铺选定原先的所属的区域
				$("#shop-area option[data-id='" + shop.area.areaId + "']").attr(
						"selected", "selected");
			}
		});
	}

	// 取得所有二级店铺类别以及区域信息，并分别赋值进类别列表以及区域列表
	function getShopInitInfo() {
		$.getJSON(initUrl, function(data) {
			if (data.success) {
				var tempHtml = '<option data-id="0">请选择</option>';
				var tempAreaHtml = '<option data-id="0">请选择</option>';
				data.shopCategoryList.map(function(item, index) {
					tempHtml += '<option data-id="' + item.shopCategoryId
							+ '">' + item.shopCategoryName + '</option>';
				});
				data.areaList.map(function(item, index) {
					tempAreaHtml += '<option data-id="' + item.areaId + '">'
							+ item.areaName + '</option>';
				});
				$('#shop-category').html(tempHtml);
				$('#shop-area').html(tempAreaHtml);
			}
		});
	}
	
	/*
	 * 初始化提交按钮
	 */
	$('#submit').click(
			function() {
				var shop = {};
				if (isEdit) {
					// 若属于编辑，则给shopId赋值
					shop.shopId = shopId;
				}
				shop.shopName = $('#shop-name').val();
				shop.shopAddr = $('#shop-addr').val();
				shop.phone = $('#shop-phone').val();
				shop.shopDesc = $('#shop-desc').val();
				shop.shopCategory = {
					shopCategoryId : $('#shop-category').find('option')
							.not(function() {
								return !this.selected;
							}).data('id')
				};
				shop.area = {
					areaId : $('#shop-area').find('option').not(function() {
						return !this.selected;
					}).data('id')
				};
				var shopImg = $('#shop-img')[0].files[0];

				var flag = checkInfo(shop);
				if (flag) {
					var formData = new FormData();
					formData.append('shopImg', shopImg);
					formData.append('shopStr', JSON.stringify(shop));
					formData.append('verifyCodeActual', $('#j_captcha').val());
					
					$.ajax({
						url : (isEdit ? editShopUrl : registerShopUrl),
						type : 'POST',
						data : formData,
						contentType : false,
						processData : false,
						cache : false,
						success : function(data) {
							if (data.success) {
								$.toast('提交成功！');
							} else {
								$.toast('提交失败！' + data.errMsg);
							}
							$('#captcha_img').click();
						}
					});
				}

			});
});



//非空校验
function checkInfo(shop) {
	console.log(shop);
	if (shop.shopName.length == 0) {
		$.toast('请输入商店名！');
		return false;
	}
	if (shop.shopCategory.shopCategoryId == 0) {
		$.toast('请选择商店类别！');
		return false;
	}
	if (shop.area.areaId == 0) {
		$.toast('请选择所属区域！');
		return false;
	}
	if (shop.shopAddr.length == 0) {
		$.toast('请输入商店地址！');
		return false;
	}
	if (shop.phone.length == 0) {
		$.toast('请输入联系电话！');
		return false;
	}
	if (shop.shopDesc.length == 0) {
		$.toast('请输入店铺详情！');
		return false;
	}	
	var verifyCodeActual = $('#j_captcha').val();
	if (!verifyCodeActual) {
		$.toast('请输入验证码！');
		return false;
	}
	
	return true;
}