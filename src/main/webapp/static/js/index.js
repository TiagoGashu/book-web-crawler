import Vue from 'vue'

import Material from 'material-design-icons'

import C from './constants.vue'
import data from './data.vue'
import store from './vuex/store.vue'

import NavBar from './components/navbar.vue'
import AddBookComp from './components/addbookcomp.vue'
import ViewBooksComp from './components/viewbookscomp.vue'
import EditBookComp from './components/editbookcomp.vue'
import Book from './components/book.vue'
import Alerts from './components/alerts.vue'
import Manga from './components/manga.vue'
import AddManga from './components/addmanga.vue'
import EditManga from './components/editmanga.vue'
import ViewMangas from './components/viewmangas.vue'
import ModalConfirm from './components/modalconfirm.vue'
import NavTabs from './components/navtabs.vue'
import App from './components/App.vue'

// registrando componentes
Vue.component(C.components.NavBar, NavBar);
Vue.component(C.components.AddBookComp, AddBookComp);
Vue.component(C.components.ViewBooksComp, ViewBooksComp);
Vue.component(C.components.EditBookComp, EditBookComp);
Vue.component(C.components.Book, Book);
Vue.component(C.components.Alerts, Alerts);
Vue.component(C.components.Manga, Manga);
Vue.component(C.components.AddManga, AddManga);
Vue.component(C.components.EditManga, EditManga);
Vue.component(C.components.ViewMangas, ViewMangas);
Vue.component(C.components.ModalConfirm, ModalConfirm);
Vue.component(C.components.NavTabs, NavTabs);
Vue.component(C.components.App, App);

new Vue({
   data,
   store,
   el: 'app', 
});
 