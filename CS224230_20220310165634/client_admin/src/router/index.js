import Vue from 'vue';
import VueRouter from 'vue-router';
import index from '../views/index.vue';
import login from '../views/login.vue';
import forgot from '../views/forgot.vue';
import register from '../views/register.vue';
Vue.use(VueRouter)

const routes = [
	// 主页
	{
		path: '/',
		name: 'index',
		component: index,
		meta: {
			index: 0,
			title: '首页'
		}
	},

	// 登录
	{
		path: '/login',
		name: 'login',
		component: login,
		meta: {
			index: 0,
			title: '登录'
		}
	},
	// 注册
	{
		path: '/register',
		name: 'register',
		component: register,
		meta: {
			index: 0,
			title: '注册'
		}
	},

	// 忘记密码
	{
		path: '/forgot',
		name: "forgot",
		component: forgot,
		meta: {
			index: 0,
			title: '忘记密码'
		}
	},

	// 修改密码
	{
		path: '/user/password',
		name: "password",
		component: () => import("../views/user/password.vue"),
		meta: {
			index: 0,
			title: '修改密码'
		}
	},

	// 视频播放页
	{
		path: "/media/video",
		name: "video",
		component: () => import('../views/media/video.vue'),
		meta: {
			index: 0,
			title: "视频"
		}
	},

	// 音频播放页
	{
		path: "/media/audio",
		name: "audio",
		component: () => import('../views/media/audio.vue'),
		meta: {
			index: 0,
			title: "音频"
		}
	},
	// 接种预约路由
	{
		path: '/vaccination_appointment/table',
		name: 'vaccination_appointment_table',
		component: () => import('../views/vaccination_appointment/table.vue')
	},
	{
		path: '/vaccination_appointment/view',
		name: 'vaccination_appointment_view',
		component: () => import('../views/vaccination_appointment/view.vue')
	},
	// 普通用户路由
	{
		path: '/ordinary_users/table',
		name: 'ordinary_users_table',
		component: () => import('../views/ordinary_users/table.vue')
	},
	{
		path: '/ordinary_users/view',
		name: 'ordinary_users_view',
		component: () => import('../views/ordinary_users/view.vue')
	},
	// 公告栏路由
	{
		path: '/bulletin_board/table',
		name: 'bulletin_board_table',
		component: () => import('../views/bulletin_board/table.vue')
	},
	{
		path: '/bulletin_board/view',
		name: 'bulletin_board_view',
		component: () => import('../views/bulletin_board/view.vue')
	},
	// 接种信息路由
	{
		path: '/vaccination_information/table',
		name: 'vaccination_information_table',
		component: () => import('../views/vaccination_information/table.vue')
	},
	{
		path: '/vaccination_information/view',
		name: 'vaccination_information_view',
		component: () => import('../views/vaccination_information/view.vue')
	},
	// 疫苗信息路由
	{
		path: '/vaccine_information/table',
		name: 'vaccine_information_table',
		component: () => import('../views/vaccine_information/table.vue')
	},
	{
		path: '/vaccine_information/view',
		name: 'vaccine_information_view',
		component: () => import('../views/vaccine_information/view.vue')
	},
	// 系统维护路由
	{
		path: '/system_maintenance/table',
		name: 'system_maintenance_table',
		component: () => import('../views/system_maintenance/table.vue')
	},
	{
		path: '/system_maintenance/view',
		name: 'system_maintenance_view',
		component: () => import('../views/system_maintenance/view.vue')
	},
	// 私密留言路由
	{
		path: '/private_message/table',
		name: 'private_message_table',
		component: () => import('../views/private_message/table.vue')
	},
	{
		path: '/private_message/view',
		name: 'private_message_view',
		component: () => import('../views/private_message/view.vue')
	},
	// 报表统计路由
	{
		path: '/report_statistics/table',
		name: 'report_statistics_table',
		component: () => import('../views/report_statistics/table.vue')
	},
	{
		path: '/report_statistics/view',
		name: 'report_statistics_view',
		component: () => import('../views/report_statistics/view.vue')
	},
	// 疫苗统计路由
	{
		path: '/vaccine_statistics/table',
		name: 'vaccine_statistics_table',
		component: () => import('../views/vaccine_statistics/table.vue')
	},
	{
		path: '/vaccine_statistics/view',
		name: 'vaccine_statistics_view',
		component: () => import('../views/vaccine_statistics/view.vue')
	},
	// 咨询电话路由
	{
		path: '/consultation_telephone/table',
		name: 'consultation_telephone_table',
		component: () => import('../views/consultation_telephone/table.vue')
	},
	{
		path: '/consultation_telephone/view',
		name: 'consultation_telephone_view',
		component: () => import('../views/consultation_telephone/view.vue')
	},
	// 公开留言路由
	{
		path: '/public_message/table',
		name: 'public_message_table',
		component: () => import('../views/public_message/table.vue')
	},
	{
		path: '/public_message/view',
		name: 'public_message_view',
		component: () => import('../views/public_message/view.vue')
	},

	// 用户路由
	{
		path: '/user/table',
		name: 'user_table',
		component: () => import('../views/user/table.vue'),
		meta: {
			index: 0,
			title: '用户列表'
		}
	},
	{
		path: '/user/view',
		name: 'user_view',
		component: () => import('../views/user/view.vue'),
		meta: {
			index: 0,
			title: '用户详情'
		}
	},
	{
		path: '/user/info',
		name: 'user_info',
		component: () => import('../views/user/info.vue'),
		meta: {
			index: 0,
			title: '个人信息'
		}
	},
	// 用户组路由
	{
		path: '/user_group/table',
		name: 'user_group_table',
		component: () => import('../views/user_group/table.vue'),
		meta: {
			index: 0,
			title: '用户组列表'
		}
	},
	{
		path: '/user_group/view',
		name: 'user_group_view',
		component: () => import('../views/user_group/view.vue'),
		meta: {
			index: 0,
			title: '用户组详情'
		}
	}
]

const router = new VueRouter({
	mode: 'history',
	base: process.env.BASE_URL,
	routes
})

router.beforeEach((to, from, next) => {
	let token = to.query.token;
	if (token){
		$.db.set("token",token,120);
	}
	next();
})

router.afterEach((to, from, next) => {
	let title = "小区疫苗接种管理系统-admin";
	document.title = title;
})

export default router
