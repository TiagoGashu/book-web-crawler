<script>
	/** store do vuex */
	import axios from 'axios'
	import Vue from 'vue'
	import Vuex from 'vuex'
	import data from '../data.vue'
	import utils from '../services/utils.vue'

	Vue.use(Vuex)
	
	const getters = {
		livros: state => state.livros,
		selectedBooks: state => state.livros.filter((l) => l.checked),
		removeAreaVisible: state => state.removeAreaVisible,
		editing: state => state.editing
	}
	
	const mutations = {
		REFRESH_BOOKS(state, responseJson) {
			if(!state.livros) {
				state.livros = [];
			}
            var livros = state.livros;
            var livrosRemover = this.getters.selectedBooks;
            livros.splice(0, livros.length);
            responseJson.forEach(function(livro) {
                livros.push(livro);
            })
        },
        TOGGLE_EDIT_BOOK(state) {
        	state.editing = !state.editing;
        },
        TOGGLE_REMOVE_AREA(state) {
        	state.removeAreaVisible = !state.removeAreaVisible;
        },
        STOP_EDITING(state) {
        	state.editing = false;
        }
	}
	
	const actions = {
		refreshBooks(context) {
            return new Promise((resolve) => {
                axios.get('http://localhost:8080/books').then((response) => {
                    context.commit('REFRESH_BOOKS', response.data);
                })
            })
        },
        saveNewBook(context, book) {
        	if(utils.canSaveBook(book)) {
				axios.post('http://localhost:8080/books', book).then(function(response) {
		            context.dispatch('refreshBooks');
		        })
			}
        },
        toggleRemoveAreaVisible(context) {
        	context.commit('TOGGLE_REMOVE_AREA');
        },
        toggleEditBook(context) {
        	var selectedBooks = this.getters.selectedBooks;
        	if(selectedBooks.length == 1) {
        		context.commit('TOGGLE_EDIT_BOOK');
        	} else {
				this.state.editing = false;        		
        	}
        },
        save(context, editedBook) {
        	if(utils.canSaveBook(editedBook)) {
				axios.post('http://localhost:8080/books', editedBook).then(function(response) {
		        	context.commit('STOP_EDITING');
		        	context.dispatch('refreshBooks');
		        })
			}
        },
        removeBooks(context) {
        	var ids = [];
			var livrosRemover = this.getters.selectedBooks;
			livrosRemover.forEach(function(livro) {
				ids.push(livro.id);
			})
			if(ids.length == 0) {
				return
			}
			
			var bookIdsParam = 'bookIds=' + ids.join(',');
			axios.delete('http://localhost:8080/books?' + bookIdsParam).then(function(response) {
				context.dispatch('refreshBooks');
			})
		}
	}
	
	var store = new Vuex.Store({
	    state: data,
	    getters,
	    mutations,
	    actions
	});
	
	export default store
</script>