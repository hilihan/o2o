-- phpMyAdmin SQL Dump
-- version 4.7.1
-- https://www.phpmyadmin.net/
--
-- Host: 10.236.158.100:7052
-- Generation Time: 2018-05-02 20:42:59
-- 服务器版本： 5.6.28-cdb2016-log
-- PHP Version: 5.6.22

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `o2o`
--

-- --------------------------------------------------------

--
-- 表的结构 `tb_area`
--

CREATE TABLE `tb_area` (
  `area_id` int(2) NOT NULL,
  `area_name` varchar(200) NOT NULL,
  `priority` int(2) NOT NULL DEFAULT '0',
  `create_time` datetime DEFAULT NULL,
  `last_edit_time` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 转存表中的数据 `tb_area`
--

INSERT INTO `tb_area` (`area_id`, `area_name`, `priority`, `create_time`, `last_edit_time`) VALUES
(2, '东苑', 1, NULL, NULL),
(3, '西苑', 2, NULL, NULL),
(4, '西湖区', 3, '2018-03-06 00:00:00', '2018-03-06 00:00:00'),
(6, '下城区', 4, '2018-03-06 00:00:00', '2018-03-06 00:00:00');

-- --------------------------------------------------------

--
-- 表的结构 `tb_head_line`
--

CREATE TABLE `tb_head_line` (
  `line_id` int(100) NOT NULL,
  `line_name` varchar(1000) DEFAULT NULL,
  `line_link` varchar(2000) NOT NULL,
  `line_img` varchar(2000) NOT NULL,
  `priority` int(2) DEFAULT NULL,
  `enable_status` int(2) NOT NULL DEFAULT '0',
  `create_time` datetime DEFAULT NULL,
  `last_edit_time` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 转存表中的数据 `tb_head_line`
--

INSERT INTO `tb_head_line` (`line_id`, `line_name`, `line_link`, `line_img`, `priority`, `enable_status`, `create_time`, `last_edit_time`) VALUES
(1, '1', '', '../resources/pic/headline/1.jpg', 1, 1, NULL, NULL),
(2, '2', '', 'http://img11.360buyimg.com//babel/jfs/t16993/292/1572478400/106528/72542594/5ad3f969N1352d172.jpg', 1, 1, NULL, NULL),
(3, '3', '', 'http://img12.360buyimg.com//babel/jfs/t18961/184/1565664621/179589/ef1bee/5acf335cNba3e3c34.jpg', 1, 1, NULL, NULL),
(4, '4', '', 'http://img13.360buyimg.com//babel/jfs/t16822/56/1474770488/227628/f5458a0d/5acb3876N19cb54ed.jpg', 1, 1, NULL, NULL),
(5, '5', '', 'http://img10.360buyimg.com//babel/jfs/t17695/72/1691149734/121502/bbcd2fe1/5ad4814fNc1ef789d.jpg', 1, 1, NULL, NULL);

-- --------------------------------------------------------

--
-- 表的结构 `tb_local_auth`
--

CREATE TABLE `tb_local_auth` (
  `local_auth_id` int(10) NOT NULL,
  `user_id` int(10) NOT NULL,
  `username` varchar(128) NOT NULL,
  `password` varchar(128) NOT NULL,
  `create_time` datetime DEFAULT NULL,
  `last_edit_time` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- 表的结构 `tb_person_info`
--

CREATE TABLE `tb_person_info` (
  `user_id` int(10) NOT NULL,
  `name` varchar(32) DEFAULT NULL,
  `profile_img` varchar(1024) DEFAULT NULL,
  `email` varchar(1024) DEFAULT NULL,
  `gender` varchar(2) DEFAULT NULL,
  `enable_status` int(2) NOT NULL DEFAULT '0' COMMENT '0:禁止使用本商城，1:允许使用本商城',
  `user_type` int(2) NOT NULL DEFAULT '1' COMMENT '1:顾客，2:店家，3:超级管理员',
  `create_time` datetime DEFAULT NULL,
  `last_edit_time` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 转存表中的数据 `tb_person_info`
--

INSERT INTO `tb_person_info` (`user_id`, `name`, `profile_img`, `email`, `gender`, `enable_status`, `user_type`, `create_time`, `last_edit_time`) VALUES
(9, '测试', 'test', 'test', '1', 1, 2, '2018-03-07 16:04:53', '2018-03-07 16:04:56');

-- --------------------------------------------------------

--
-- 表的结构 `tb_product`
--

CREATE TABLE `tb_product` (
  `product_id` int(100) NOT NULL,
  `product_name` varchar(100) NOT NULL,
  `product_desc` varchar(2000) DEFAULT NULL,
  `img_addr` varchar(2000) DEFAULT '',
  `normal_price` varchar(100) DEFAULT NULL,
  `promotion_price` varchar(100) DEFAULT NULL,
  `priority` int(2) NOT NULL DEFAULT '0',
  `create_time` datetime DEFAULT NULL,
  `last_edit_time` datetime DEFAULT NULL,
  `enable_status` int(2) NOT NULL DEFAULT '0',
  `product_category_id` int(11) DEFAULT NULL,
  `shop_id` int(20) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 转存表中的数据 `tb_product`
--

INSERT INTO `tb_product` (`product_id`, `product_name`, `product_desc`, `img_addr`, `normal_price`, `promotion_price`, `priority`, `create_time`, `last_edit_time`, `enable_status`, `product_category_id`, `shop_id`) VALUES
(1, '新奥尔良烤鸡腿堡S', '选用超大无骨鸡腿肉烤制，鲜嫩多汁，甜中带辣。\n主要原料:鸡腿肉，面包，蔬菜（生菜，青椒丝）', 'upload/item/shop/41/2018040616490812155.jpg', '18.5', '', 1, '2018-03-31 09:49:23', '2018-04-06 16:49:08', 1, 33, 41),
(2, '测试2', '测试Desc2', 'test2', NULL, NULL, 2, '2018-03-31 09:49:23', '2018-03-31 09:49:23', 0, 51, 41),
(3, '测试3', '测试Desc3', 'test3', NULL, NULL, 3, '2018-03-31 09:49:23', '2018-03-31 09:49:23', 1, 32, 41),
(4, '嫩牛五方翅桶', '辣翅6块+烤翅4块+面包升级香辣鸡腿堡1个+川辣嫩牛五方1个+九珍2杯', 'upload/item/shop/41/2018040316492883673.jpg', NULL, NULL, 10, '2018-04-03 16:49:28', '2018-04-03 16:49:28', 1, 32, 41),
(5, 'WOW桶随心配', 'WOW桶内指定产品单品总价满59元（不含外送费）可享WOW桶8折优惠；WOW桶内指定产品单品总价满99元（不含外送费）再免外送费。点WOW桶100%得QQ绿钻会员！', 'upload/item/shop/41/2018040516535591946.jpg', '0', '0', 9, '2018-04-05 16:53:55', '2018-04-05 16:53:55', 1, 44, 41);

-- --------------------------------------------------------

--
-- 表的结构 `tb_product_category`
--

CREATE TABLE `tb_product_category` (
  `product_category_id` int(11) NOT NULL,
  `product_category_name` varchar(100) NOT NULL,
  `priority` int(2) DEFAULT '0',
  `create_time` datetime DEFAULT NULL,
  `shop_id` int(20) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 转存表中的数据 `tb_product_category`
--

INSERT INTO `tb_product_category` (`product_category_id`, `product_category_name`, `priority`, `create_time`, `shop_id`) VALUES
(32, '桶', 1, NULL, 41),
(33, '美味汉堡/卷', 1, NULL, 41),
(34, '人气明星餐', 1, NULL, 41),
(35, 'K记饭桶', 1, NULL, 41),
(36, '鸡翅鸡排', 2, NULL, 41),
(37, '吮指原味鸡', 3, NULL, 41),
(38, '小食/配餐', 4, NULL, 41),
(39, '甜品/冰淇淋', 5, NULL, 41),
(44, '当季主打', 10, NULL, 41),
(45, 'K Coffee', 3, NULL, 41),
(51, '自助晚餐', 3, '2018-04-17 11:15:31', 41);

-- --------------------------------------------------------

--
-- 表的结构 `tb_product_img`
--

CREATE TABLE `tb_product_img` (
  `product_img_id` int(20) NOT NULL,
  `img_addr` varchar(2000) NOT NULL,
  `img_desc` varchar(2000) DEFAULT NULL,
  `priority` int(2) DEFAULT '0',
  `create_time` datetime DEFAULT NULL,
  `product_id` int(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 转存表中的数据 `tb_product_img`
--

INSERT INTO `tb_product_img` (`product_img_id`, `img_addr`, `img_desc`, `priority`, `create_time`, `product_id`) VALUES
(3, 'upload/item/shop/41/2018040316492982245.jpeg', NULL, NULL, '2018-04-03 16:49:29', 4),
(4, 'upload/item/shop/41/2018040316492985705.jpeg', NULL, NULL, '2018-04-03 16:49:29', 4),
(5, 'upload/item/shop/41/2018040516535579972.jpeg', NULL, NULL, '2018-04-05 16:53:55', 5),
(6, 'upload/item/shop/41/2018040516535512876.jpeg', NULL, NULL, '2018-04-05 16:53:55', 5),
(9, 'upload/item/shop/41/2018040616490822644.jpg', NULL, NULL, '2018-04-06 16:49:08', 1),
(10, 'upload/item/shop/41/2018040616490818695.jpg', NULL, NULL, '2018-04-06 16:49:08', 1);

-- --------------------------------------------------------

--
-- 表的结构 `tb_shop`
--

CREATE TABLE `tb_shop` (
  `shop_id` int(10) NOT NULL,
  `owner_id` int(10) NOT NULL COMMENT '店铺创建人',
  `area_id` int(5) DEFAULT NULL,
  `shop_category_id` int(11) DEFAULT NULL,
  `shop_name` varchar(256) NOT NULL,
  `shop_desc` varchar(1024) DEFAULT NULL,
  `shop_addr` varchar(200) DEFAULT NULL,
  `phone` varchar(128) DEFAULT NULL,
  `shop_img` varchar(1024) DEFAULT NULL,
  `priority` int(3) DEFAULT '0',
  `create_time` datetime DEFAULT NULL,
  `last_edit_time` datetime DEFAULT NULL,
  `enable_status` int(2) NOT NULL DEFAULT '0',
  `advice` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 转存表中的数据 `tb_shop`
--

INSERT INTO `tb_shop` (`shop_id`, `owner_id`, `area_id`, `shop_category_id`, `shop_name`, `shop_desc`, `shop_addr`, `phone`, `shop_img`, `priority`, `create_time`, `last_edit_time`, `enable_status`, `advice`) VALUES
(1, 9, 4, 38, '斯利美', '香甜入心间', '西湖区文三西路255-1号（息来庄假日酒店旁边', '0571-87038835', '/upload/item/shop/1/slm.jpg', 0, '2018-04-26 10:34:08', '2018-04-26 10:34:08', 1, NULL),
(2, 9, 6, 20, '鲜森果品', '我们只做新鲜水果的搬运工', '和汇路17-1号', '18767133875', '/upload/item/shop/2/xsgp.jpg', 0, '2018-05-02 10:34:08', '2018-05-02 10:34:08', 1, NULL),
(3, 9, 6, 28, '零食营行量版零食连锁', '零食营行', '城隍中路150号', '15025618999', '/upload/item/shop/3/lsyh.jpg', 0, '2018-05-03 10:34:08', '2018-05-03 10:34:08', 1, NULL),
(4, 9, 4, 28, '格格驾到零食便利（学林街店）', '格格驾到', '杭州经济技术开发区下沙街道学林街1370号', '13750823950', '/upload/item/shop/4/ggjd.jpg', 0, '2018-05-03 10:34:08', '2018-05-03 10:34:08', 1, NULL),
(39, 9, 4, 39, '味千拉面', '味千（中国）控股有限公司是一家以内地为主营业务基地的经营商。截至2012年底，味千中国的快速休闲连锁餐厅网络遍布中国120个主要城市的商业地段，在上海、香港、北京、深圳、广州、杭州、南京、福州、大连、成都、武汉等地区设有661家分店，2012年营业额达30.43亿港元。', '文二路世纪联华一楼', '88585555', '/upload/item/shop/39/2018032109572687602.jpg', 0, '2018-03-10 10:34:08', '2018-03-23 10:25:01', 1, '审核通过'),
(41, 9, 4, 37, 'KFC', '666', '文三路259号', '666666', '/upload/item/shop/41/2018031611133247785.jpg', 0, '2018-03-16 11:13:32', '2018-03-16 11:13:32', 1, NULL);

-- --------------------------------------------------------

--
-- 表的结构 `tb_shop_category`
--

CREATE TABLE `tb_shop_category` (
  `shop_category_id` int(11) NOT NULL,
  `shop_category_name` varchar(100) NOT NULL DEFAULT '',
  `shop_category_desc` varchar(1000) DEFAULT '',
  `shop_category_img` varchar(2000) DEFAULT NULL,
  `priority` int(2) NOT NULL DEFAULT '0',
  `create_time` datetime DEFAULT NULL,
  `last_edit_time` datetime DEFAULT NULL,
  `parent_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 转存表中的数据 `tb_shop_category`
--

INSERT INTO `tb_shop_category` (`shop_category_id`, `shop_category_name`, `shop_category_desc`, `shop_category_img`, `priority`, `create_time`, `last_edit_time`, `parent_id`) VALUES
(7, '母婴用品', '母婴用品', '/upload/item/shopcategory/stroller.png', 100, NULL, NULL, NULL),
(8, '有钱生鲜', '随鲜随送', '/upload/item/shopcategory/24-hours.png', 2, NULL, NULL, NULL),
(9, '家具清洁', '家具清洁', '/upload/item/shopcategory/house-things.png', 92, NULL, NULL, NULL),
(10, '二手市场', '二手商品交易', '/upload/item/shopcategory/ershou.png', 100, NULL, NULL, NULL),
(11, '美容美发', '美容美发', '/upload/item/shopcategory/lipstick.png', 99, NULL, NULL, NULL),
(12, '美食饮品', '美食饮品', '/upload/item/shopcategory/donut.png', 98, NULL, NULL, NULL),
(13, '休闲娱乐', '休闲娱乐', '/upload/item/shopcategory/ticket.png', 97, NULL, NULL, NULL),
(14, '培训教育', '培训教育', '/upload/item/shopcategory/presentation.png', 96, NULL, NULL, NULL),
(15, '租赁市场', '租赁市场', '/upload/item/shopcategory/rent.png', 95, NULL, NULL, NULL),
(16, '旧车', '旧车', NULL, 80, NULL, NULL, 10),
(17, '二手书籍', '二手书籍', NULL, 94, NULL, NULL, 10),
(18, '面部护肤', '面部护肤', NULL, 88, NULL, NULL, 11),
(19, '美容工具', '美容工具', NULL, 89, NULL, NULL, 11),
(20, '新鲜水果', '新鲜水果', NULL, 86, NULL, NULL, 8),
(21, '海鲜水产', '海鲜水产', NULL, 87, NULL, NULL, 8),
(22, '冷冻速食', '冷冻速食', NULL, 85, NULL, NULL, 8),
(23, '奶粉', '奶粉', NULL, 83, NULL, NULL, 7),
(24, '尿裤湿巾', '尿裤湿巾', NULL, 84, NULL, NULL, 7),
(25, '纸巾湿巾', '纸巾湿巾', NULL, 81, NULL, NULL, 9),
(26, '衣物清洁', '衣物清洁', NULL, 82, NULL, NULL, 9),
(27, '进口食品', '进口食品', NULL, 79, NULL, NULL, 12),
(28, '休闲食品', '休闲食品', NULL, 80, NULL, NULL, 12),
(29, '蛋糕饼干', '蛋糕饼干', NULL, 78, NULL, NULL, 12),
(36, '下午茶', '下午茶', '/upload/item/shopcategory/tea.png', 1, '2018-03-07 16:06:12', '2018-03-07 16:06:14', NULL),
(37, '炸鸡汉堡', '炸鸡汉堡', '', 2, '2018-03-14 10:25:12', '2018-03-14 10:25:12', 36),
(38, '甜点饮品', '过一个甜蜜的下午', NULL, 0, '2018-04-23 17:01:12', NULL, 36),
(39, '日韩料理', '异国风味', NULL, 0, NULL, NULL, 36);

-- --------------------------------------------------------

--
-- 表的结构 `tb_wechat_auth`
--

CREATE TABLE `tb_wechat_auth` (
  `wechat_auth_id` int(10) NOT NULL,
  `user_id` int(10) NOT NULL,
  `open_id` varchar(80) NOT NULL DEFAULT '',
  `create_time` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `tb_area`
--
ALTER TABLE `tb_area`
  ADD PRIMARY KEY (`area_id`),
  ADD UNIQUE KEY `UK_AREA` (`area_name`);

--
-- Indexes for table `tb_head_line`
--
ALTER TABLE `tb_head_line`
  ADD PRIMARY KEY (`line_id`);

--
-- Indexes for table `tb_local_auth`
--
ALTER TABLE `tb_local_auth`
  ADD PRIMARY KEY (`local_auth_id`),
  ADD UNIQUE KEY `uk_local_profile` (`username`),
  ADD KEY `fk_localauth_profile` (`user_id`);

--
-- Indexes for table `tb_person_info`
--
ALTER TABLE `tb_person_info`
  ADD PRIMARY KEY (`user_id`);

--
-- Indexes for table `tb_product`
--
ALTER TABLE `tb_product`
  ADD PRIMARY KEY (`product_id`),
  ADD KEY `fk_product_procate` (`product_category_id`),
  ADD KEY `fk_product_shop` (`shop_id`);

--
-- Indexes for table `tb_product_category`
--
ALTER TABLE `tb_product_category`
  ADD PRIMARY KEY (`product_category_id`),
  ADD KEY `fk_procate_shop` (`shop_id`);

--
-- Indexes for table `tb_product_img`
--
ALTER TABLE `tb_product_img`
  ADD PRIMARY KEY (`product_img_id`),
  ADD KEY `fk_proimg_product` (`product_id`);

--
-- Indexes for table `tb_shop`
--
ALTER TABLE `tb_shop`
  ADD PRIMARY KEY (`shop_id`),
  ADD KEY `fk_shop_area` (`area_id`),
  ADD KEY `fk_shop_profile` (`owner_id`),
  ADD KEY `fk_shop_shopcate` (`shop_category_id`);

--
-- Indexes for table `tb_shop_category`
--
ALTER TABLE `tb_shop_category`
  ADD PRIMARY KEY (`shop_category_id`),
  ADD KEY `fk_shop_category_self` (`parent_id`);

--
-- Indexes for table `tb_wechat_auth`
--
ALTER TABLE `tb_wechat_auth`
  ADD PRIMARY KEY (`wechat_auth_id`),
  ADD UNIQUE KEY `open_id` (`open_id`),
  ADD KEY `fk_wechatauth_profile` (`user_id`);

--
-- 在导出的表使用AUTO_INCREMENT
--

--
-- 使用表AUTO_INCREMENT `tb_area`
--
ALTER TABLE `tb_area`
  MODIFY `area_id` int(2) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
--
-- 使用表AUTO_INCREMENT `tb_head_line`
--
ALTER TABLE `tb_head_line`
  MODIFY `line_id` int(100) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
--
-- 使用表AUTO_INCREMENT `tb_local_auth`
--
ALTER TABLE `tb_local_auth`
  MODIFY `local_auth_id` int(10) NOT NULL AUTO_INCREMENT;
--
-- 使用表AUTO_INCREMENT `tb_person_info`
--
ALTER TABLE `tb_person_info`
  MODIFY `user_id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;
--
-- 使用表AUTO_INCREMENT `tb_product`
--
ALTER TABLE `tb_product`
  MODIFY `product_id` int(100) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
--
-- 使用表AUTO_INCREMENT `tb_product_category`
--
ALTER TABLE `tb_product_category`
  MODIFY `product_category_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=52;
--
-- 使用表AUTO_INCREMENT `tb_product_img`
--
ALTER TABLE `tb_product_img`
  MODIFY `product_img_id` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;
--
-- 使用表AUTO_INCREMENT `tb_shop`
--
ALTER TABLE `tb_shop`
  MODIFY `shop_id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=43;
--
-- 使用表AUTO_INCREMENT `tb_shop_category`
--
ALTER TABLE `tb_shop_category`
  MODIFY `shop_category_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=40;
--
-- 使用表AUTO_INCREMENT `tb_wechat_auth`
--
ALTER TABLE `tb_wechat_auth`
  MODIFY `wechat_auth_id` int(10) NOT NULL AUTO_INCREMENT;
--
-- 限制导出的表
--

--
-- 限制表 `tb_local_auth`
--
ALTER TABLE `tb_local_auth`
  ADD CONSTRAINT `fk_localauth_profile` FOREIGN KEY (`user_id`) REFERENCES `tb_person_info` (`user_id`);

--
-- 限制表 `tb_product`
--
ALTER TABLE `tb_product`
  ADD CONSTRAINT `fk_product_procate` FOREIGN KEY (`product_category_id`) REFERENCES `tb_product_category` (`product_category_id`),
  ADD CONSTRAINT `fk_product_shop` FOREIGN KEY (`shop_id`) REFERENCES `tb_shop` (`shop_id`);

--
-- 限制表 `tb_product_category`
--
ALTER TABLE `tb_product_category`
  ADD CONSTRAINT `fk_procate_shop` FOREIGN KEY (`shop_id`) REFERENCES `tb_shop` (`shop_id`);

--
-- 限制表 `tb_product_img`
--
ALTER TABLE `tb_product_img`
  ADD CONSTRAINT `fk_proimg_product` FOREIGN KEY (`product_id`) REFERENCES `tb_product` (`product_id`);

--
-- 限制表 `tb_shop`
--
ALTER TABLE `tb_shop`
  ADD CONSTRAINT `fk_shop_area` FOREIGN KEY (`area_id`) REFERENCES `tb_area` (`area_id`),
  ADD CONSTRAINT `fk_shop_profile` FOREIGN KEY (`owner_id`) REFERENCES `tb_person_info` (`user_id`),
  ADD CONSTRAINT `fk_shop_shopcate` FOREIGN KEY (`shop_category_id`) REFERENCES `tb_shop_category` (`shop_category_id`);

--
-- 限制表 `tb_shop_category`
--
ALTER TABLE `tb_shop_category`
  ADD CONSTRAINT `fk_shop_category_self` FOREIGN KEY (`parent_id`) REFERENCES `tb_shop_category` (`shop_category_id`);

--
-- 限制表 `tb_wechat_auth`
--
ALTER TABLE `tb_wechat_auth`
  ADD CONSTRAINT `fk_wechatauth_profile` FOREIGN KEY (`user_id`) REFERENCES `tb_person_info` (`user_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
