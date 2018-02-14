import Vue from 'vue'

import C from './components/constants.vue'
import data from './components/data.vue'

// componentes de tela
import NavBar from './components/navbar.vue'
import AddBookComp from './components/addbookcomp.vue'
import ViewBooksComp from './components/viewbookscomp.vue'
import EditBookComp from './components/editbookcomp.vue'
import RemoveBooksComp from './components/removebookscomp.vue'

// registrando componentes
Vue.component(C.components.NavBar, NavBar);
Vue.component(C.components.AddBookComp, AddBookComp);
Vue.component(C.components.ViewBooksComp, ViewBooksComp);
Vue.component(C.components.EditBookComp, EditBookComp);
Vue.component(C.components.RemoveBooksComp, RemoveBooksComp);

 new Vue({ 
   el: '#app', 
   data: data 
 });
 