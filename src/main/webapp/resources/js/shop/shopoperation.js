// 取得所有二级店铺类别以及区域信息，并分别赋值进类别列表以及区域列表
$(function() {
	var initUrl = '/o2o/shopadmin/getshopinitinfo';
	var registerShopUrl = '/o2o/shopadmin/registershop';

	getShopInitInfo();

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
		/*
		 * 初始化提交按钮0
		 */
		$('#submit').click(
				function() {
					var shop = {};
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
							url : registerShopUrl,
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
	}
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