<template>
	<div>
		<el-form-item v-if="$check_register_field('add','user_name','/ordinary_users/view')" label="用户名" prop="user_name">
		<el-input id="user_name" v-model="form['user_name']" placeholder="请输入用户名"></el-input>
		</el-form-item>
		<el-form-item v-if="$check_register_field('add','full_name','/ordinary_users/view')" label="姓名" prop="full_name">
		<el-input id="full_name" v-model="form['full_name']" placeholder="请输入姓名"></el-input>
		</el-form-item>
		<el-form-item v-if="$check_register_field('add','gender','/ordinary_users/view')" label="性别" prop="gender">
		<el-select id="gender" v-model="form['gender']">
			<el-option v-for="o in list_gender" :key="o" :label="o" :value="o">
			</el-option>
		</el-select>
		</el-form-item>
		<el-form-item v-if="$check_register_field('add','age','/ordinary_users/view')" label="年龄" prop="age">
		<el-input id="age" v-model="form['age']" placeholder="请输入年龄"></el-input>
		</el-form-item>
		<el-form-item v-if="$check_register_field('add','id','/ordinary_users/view')" label="身份证" prop="id">
		<el-input id="id" v-model="form['id']" placeholder="请输入身份证"></el-input>
		</el-form-item>
		<el-form-item v-if="$check_register_field('add','community','/ordinary_users/view')" label="社区" prop="community">
		<el-input id="community" v-model="form['community']" placeholder="请输入社区"></el-input>
		</el-form-item>
		<el-form-item v-if="$check_register_field('add','unit','/ordinary_users/view')" label="单元" prop="unit">
		<el-input id="unit" v-model="form['unit']" placeholder="请输入单元"></el-input>
		</el-form-item>
		<el-form-item v-if="$check_register_field('add','floor','/ordinary_users/view')" label="楼层" prop="floor">
		<el-input id="floor" v-model="form['floor']" placeholder="请输入楼层"></el-input>
		</el-form-item>
		<el-form-item v-if="$check_register_field('add','house_number','/ordinary_users/view')" label="门牌号" prop="house_number">
		<el-input id="house_number" v-model="form['house_number']" placeholder="请输入门牌号"></el-input>
		</el-form-item>

	</div>
</template>

<script>
	import mixin from "@/mixins/component.js";

	export default {
		mixins: [mixin],
		props:{

			form:{
				type: Object,
				default: function(){
					return {
						"user_name":'',
						"full_name":'',
						"gender":'',
						"age":'',
						"id":'',
						"community":'',
						"unit":'',
						"floor":'',
						"house_number":'',
					}
				}
			},

		},
		data() {
			return {
				field: "ordinary_users_id",
				url_add: "~/api/ordinary_users/add?",
				url_set: "~/api/ordinary_users/set?",
				url_upload: "~/api/ordinary_users/upload?",
				list_gender: ['男','女'],

				rules: {
					"user_name": [ {required: true,message: '用户名不能为空'}, ],
					"full_name": [ ],
					"gender": [ ],
					"age": [ ],
					"id": [ ],
					"community": [ ],
					"unit": [ ],
					"floor": [ ],
					"house_number": [ ],
				}

			}
		},
		methods: {

		},
		created() {
		}
	}
</script>
