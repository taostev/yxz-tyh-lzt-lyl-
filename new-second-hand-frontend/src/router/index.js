import { createRouter, createWebHistory } from 'vue-router'
import UserLogin from '../views/Login.vue'
import UserRegister from '../views/Register.vue'
import UserDashboard from '../views/Dashboard.vue'

const routes = [
  { path: '/', redirect: '/login' },
  { path: '/login', component: UserLogin },
  { path: '/register', component: UserRegister },
  { path: '/dashboard', component: UserDashboard }
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

export default router