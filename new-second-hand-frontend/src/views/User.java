<template>
  <div>
    <h2>用户注册</h2>
    <form @submit.prevent="onRegister">
      <input v-model="form.username" placeholder="用户名" required />
      <input v-model="form.password" type="password" placeholder="密码" required />
      <input v-model="form.phone" placeholder="手机号" />
      <input v-model="form.email" placeholder="邮箱" />
      <button type="submit">注册</button>
    </form>
    <p>{{ msg }}</p>
  </div>
</template>
<script>
import { register } from '@/api/user';
export default {
  data() {
    return {
      form: { username: '', password: '', phone: '', email: '' },
      msg: ''
    }
  },
  methods: {
    onRegister() {
      register(this.form).then(res => {
        this.msg = res.data;
        if (res.data === '注册成功') this.$router.push('/login');
      });
    }
  }
}
</script>