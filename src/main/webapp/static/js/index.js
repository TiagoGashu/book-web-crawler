//axios = require('axios')
//require('vue')
//require('vuex')

// index.js INI

var C = {
    components: {
	    'NavBar': 'nav-bar',
	    'ViewBooksComp': 'view-books-comp',
	    'AddBookComp': 'add-book-comp',
	    'RemoveBooksComp': 'remove-books-comp'
    }
}

var navigation = {
    itens: [
    	{name: 'Add Book', componentName: C.components.AddBookComp, active: false}, 
    	{name: 'View My Books', componentName: C.components.ViewBooksComp, active: false},
    	// {name: 'Remove Books', componentName: C.components.RemoveBooksComp, active: false}
    ],
    activeItem: null,
    getActiveItem: function() {
    	if(this.activeItem != null && activeItem.active) {
    		return this.activeItem;
    	}
    	
    	var itens = this.itens;
    	var active = null;
    	itens.forEach(function(item) {
    		if(active == null && item.active) {
    			active = item;
    		}
    	});
    	return active;
    }
}

/** mixin para ser usado na exibição dos componentes controlados pela navegação */
var navigationMixin = {
    computed: {
    	isActive: function() {
    		var activeItem = store.state.navigation.getActiveItem();
    		return activeItem && this.componentName == activeItem.componentName;
    	}
    }
}

var data = {
   navigation: navigation,
   livros: [],
   livrosRemover: [],
   header: 'Favorite Books', 
   novoLivro: {
	   name: '',
	   authorName: ''
   },
   editing: false,
   editLivro: {
	   name: '',
	   authorName: ''
   },
   removeAreaVisible: false
};

/** vuex store para centralizar a atualização dos livros */
store = new Vuex.Store({
    state: data,
    mutations: {
        updateLivros(state, responseJson) {
            var livros = this.state.livros;
            var livrosRemover = this.state.livrosRemover;
            livros.splice(0, livros.length);
            responseJson.forEach(function(livro) {
                livros.push(livro);
            })
            livrosRemover.splice(0, livrosRemover.length);
        }
    },
    actions: {
        refreshLivros(context) {
            return new Promise((resolve) => {
                axios.get('http://localhost:8080/books').then((response) => {
                    context.commit('updateLivros', response.data);
                })
            })
        }
    }
});
 
 /** 
 * Declarando os componentes
  */

/** componente de navegação */
var NavBar = Vue.extend({
	 data: function() {
		 return store.state;
	 },
	 methods: {
		 navigate: function(activeItem, event) {
			 var navigation = store.state.navigation;
			 navigation.itens.forEach(function(item) {
				 item.active = false;
			 })
			 activeItem.active = true;
		 }
	 },
	 template:
	  '<div>' +
	  '    <ul class="nav nav-tabs">' +
      '        <li v-for="item in navigation.itens" class="nav-item">' +
      '            <a class="nav-link" :class="{\'active\': item.active}" @click="navigate(item, $event)" href="#">' +
      '                {{item.name}}' +
      '            </a>' +
      '        </li>' +
	  '    </ul>' +
	  '</div>'
})

/** componente para exibição e seleção de livros */
 var ViewBooksComp = Vue.extend({
   mixins: [navigationMixin],
   data: function () {
     return {
    	 root: store.state,
    	 componentName: C.components.ViewBooksComp
     }
   }, 
   methods: {
	   updateLivrosRemover(livro, event) {
		   var livrosRemover = store.state.livrosRemover;
		   var found = livrosRemover.find(function(l) {
			   return livro.id == l.id;
		   })
		   if(livro.checked) {
			   if(!found) {
				   livrosRemover.push(livro);
			   }
		   } else if(found) {
			   livrosRemover.splice(livrosRemover.indexOf(found), 1);
		   }
	   }
   },
   mounted: function() {
	   store.dispatch('refreshLivros')
   },
   template:
	'<div v-show="isActive">' +
	'    <div style="max-height: 255px; overflow: auto;">' +
	'        <ul class="list-group">' + 
    '            <li class="list-group-item" v-for="livro in root.livros" :class="{ \'active\': livro.checked }">' + 
    '                <div class="checkbox">' + 
    '                    <input type="checkbox" v-model="livro.checked" :change="updateLivrosRemover(livro)">' + 
    '                    {{ livro.name }} - <small>{{ livro.authorName }}</small>' + 
    '                    <span>id: {{livro.id}}</span>' +
    '                </div>' +  
    '            </li>' + 
    '        </ul>' +
    '    </div>' +
    '    <edit-book-comp></edit-book-comp>' +
    '</div>'
 });

var saveBook = function(book) {
	if(book && book.name && book.authorName) {
		axios.post('http://localhost:8080/books', book).then(function(response) {
            store.dispatch('refreshLivros');
        })
	}
}

var EditBookComp = Vue.extend({
	data: function() {
		return store.state;
	},
    methods: {
    	toggleEditBook: function() {
    		var data = store.state;
    		
    		if(data.editing) {
    			data.editLivro = {name: '', authorName: ''}
    		}
    		
    		data.editing = !data.editing;
    		
    		if(data.editing && data.livrosRemover.length == 1) {
    			var livroSendoEditado = data.livrosRemover[0];
    			data.editLivro.id = livroSendoEditado.id
    			data.editLivro.name = livroSendoEditado.name
    			data.editLivro.authorName = livroSendoEditado.authorName
    		}
    	},
    	save: function() {
    		var editLivro = store.state.editLivro;
    		saveBook(editLivro);
    		data.editing = false;
    		store.state.editLivro = {};
    	}
    },
    template:
     '<div v-show="livrosRemover.length == 1">' +
     '    <div class="editing-container" :class="{\'not-editing\': !editing}"  v-show="editing">' +
     '        <div style="text-align: center;">' +
     '            <big>Book Editing</big>' +
     '        </div>' +
     '        <div class="editBook">' +
     '            <div class="input-group">' +
     '                <div class="input-group-prepend mb-3">' +
     '                <span class="input-group-text">Book&apos;s name</span>' +
     '                </div>' +
     '                <input v-model="editLivro.name" @keyup.enter="save"' +
     '              type="text" class="form-control" style="height: 38px;"/>'  +
     '            </div>' +
     '            <div class="input-group">' +
     '                <div class="input-group-prepend mb-3">' +
     '                    <span class="input-group-text">Author&apos;s name</span>' +
     '                </div>' +
     '                <input v-model="editLivro.authorName" @keyup.enter="addLivro"' +
     '                  type="text" class="form-control" style="height: 38px;"/>'  +
     '            </div>' +
     '        </div>' +
     '        <button class="btn btn-primary" type="button" @click="save" ' + 
     '           v-show="editing && editLivro.name && editLivro.authorName">' + 
     '            Save' +
     '        </button>' +
     '    </div>' +
     '    <button class="btn btn-primary" type="button" @click="toggleEditBook"' +
     '         style="margin-top: 2px;">{{ editing ? \'Disable Edit\' : \'Enable Edit\' }}</button>' +
     '</div>'
})

/** componente para adicionar livro */
var AddBookComp = Vue.extend({
    mixins: [navigationMixin],
    data: function() { 
        return {
            root: store.state,
            componentName: C.components.AddBookComp
        }
    }, 
    methods: {
        addLivro: function () { 
            var novoLivro = store.state.novoLivro;
            saveBook(novoLivro);
            this.root.novoLivro = {}
        } 
    }, 
    template: 
     '<div class="editing-container" v-show="isActive">' +
     '    <div class="input-group">' +
     '        <div class="input-group-prepend mb-3">' +
     '           <span class="input-group-text">Book&apos;s name</span>' +
     '        </div>' +
     '        <input v-model="root.novoLivro.name" @keyup.enter="addLivro"' +
     '            type="text" class="form-control" style="height: 38px;"/>'  +
     '    </div>' +
     '    <div class="input-group">' +
     '        <div class="input-group-prepend mb-3">' +
     '           <span class="input-group-text">Author&apos;s name</span>' +
     '        </div>' +
     '        <input v-model="root.novoLivro.authorName" @keyup.enter="addLivro"' +
     '            type="text" class="form-control" style="height: 38px;"/>'  +
     '    </div>' +
     '    <div class="input-group-btn" v-show="root.novoLivro != \'\' && root.novoAutor != \'\'">' + 
     '        <button @click="addLivro" class="js-add btn btn-primary"' +
     '            type="button">Add Book</button>'  + 
     '    </div>' +
     '</div>'
 });
 
/** componente para remoção de livros */
 var RemoveBooksComp = Vue.extend({
	mixins: [navigationMixin],
	data: function() {
		return {
	        root: store.state,
	        componentName: C.components.ViewBooksComp
	    }
	},
	methods: {
		toggleRemoveAreaVisible: function() {
			store.state.removeAreaVisible = !store.state.removeAreaVisible;
		},
		removeBooks: function() {
			var ids = [];
			var livrosRemover = this.root.livrosRemover;
			livrosRemover.forEach(function(livro) {
				ids.push(livro.id);
			})
			if(ids.length == 0) {
				return
			}
			var bookIdsParam = 'bookIds=' + ids.join(',');
			axios.delete('http://localhost:8080/books?' + bookIdsParam).then(function(response) {
				store.dispatch('refreshLivros');
			})
		}
	},
	computed: {
		plural: function() {
			if(store.state.livrosRemover.length > 1) {
				return 's';
			} else {
				return '';
			}
		}
	},
	template:
     '<div v-show="isActive" style="margin-top: 16px;">' +
     '    <button class="btn btn-primary" type="button"@click="root.removeAreaVisible = !root.removeAreaVisible">' +
     '        Toogle remove Area' +
     '    </button>' +
     '    <transition name="fade">' +
     '        <div id="removeArea" style="text-align: center;" v-show="root.removeAreaVisible">' +
     '            <ul class="list-group">' +
     '                <li class="list-group-item">' + 
     '                    - Book{{plural}} to remove -' + 
     '                </li>' +
     '                <li class="list-group-item" v-for="livro in root.livrosRemover">' +
     '                    {{livro.name}} - {{livro.authorName}}' +
     '                </li>' +
     '            </ul>' +
     '            <div v-show="root.livrosRemover.length > 0" ' +
     '                    style="margin-top: 6px; position: absolute; right: 0; margin-right: 15px;">' +
     '                <div class="col-sm col-sm-offset-10">' +
     '                    <button@click="removeBooks" class="js-remove btn btn-primary"' +
     '                      type="button">Remove Book{{plural}}</button>' +
     '                </div>' +
     '            </div>' +
     '        </div>' +
     '    </transition>' +
     '</div>'
 });
 
 /** 
  * Registrando componentes
  */
 Vue.component('nav-bar', NavBar);
 Vue.component('view-books-comp', ViewBooksComp);
 Vue.component('edit-book-comp', EditBookComp);
 Vue.component('add-book-comp', AddBookComp);
 Vue.component('remove-books-comp', RemoveBooksComp);
 
 /** 
  * Instanciando o Vue
  */ 
 new Vue({ 
   el: '#app', 
   data: data 
 });
 
 // index.js FIM