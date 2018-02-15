import Vue from 'vue'

import C from './constants.vue'
import data from './data.vue'
import store from './vuex/store.vue'

import NavBar from './components/navbar.vue'
import AddBookComp from './components/addbookcomp.vue'
import ViewBooksComp from './components/viewbookscomp.vue'
import EditBookComp from './components/editbookcomp.vue'
import RemoveBooksComp from './components/removebookscomp.vue'
import Book from './components/book.vue'
import App from './components/App.vue'

// registrando componentes
Vue.component(C.components.NavBar, NavBar);
Vue.component(C.components.AddBookComp, AddBookComp);
Vue.component(C.components.ViewBooksComp, ViewBooksComp);
Vue.component(C.components.EditBookComp, EditBookComp);
Vue.component(C.components.RemoveBooksComp, RemoveBooksComp);
Vue.component(C.components.Book, Book);
Vue.component(C.components.App, App);

new Vue({
   data,
   store,
   el: 'app', 
});
 