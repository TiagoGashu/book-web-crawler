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
	
	const commonFunctions = {
		saveBook(params, editedBook, stopEditing) {
			var context, resolve, reject;
			({context, resolve, reject} = params);
			if(!utils.canSaveBook(editedBook)) {
				context.commit('SHOW_CANT_SAVE_MSG');
				reject();	
				return
			}
			axios.post('http://localhost:8080/books', editedBook).then((response) => {
				resolve();
				if(stopEditing) {
					context.commit('STOP_EDITING');
				}
				context.commit('SHOW_SAVE_SUCCESS_MSG', response.data);
				context.dispatch('refreshBooks');
			});
		},
		removeBooks(context, ids) {
			var bookIdsParam = 'bookIds=' + ids.join(',');
			axios.delete('http://localhost:8080/books?' + bookIdsParam).then(function(response) {
				context.commit('SHOW_REMOVE_SUCCESS_MSG', response.data);
				context.dispatch('refreshBooks', response.data);
			})
		}
	}
	
	const editMutations = {
		TOGGLE_EDIT_BOOK(state) {
        	state.editing = !state.editing;
        },
        STOP_EDITING(state) {
        	state.editing = false;
        }
	}
	
	const messageMutations = {
		SHOW_SAVE_SUCCESS_MSG(state, book) {
        	state.alerts.push({success: true, msg: 'O livro "' + book.name +'" foi salvo com sucesso!'});
        },
        SHOW_REMOVE_SUCCESS_MSG(state, data) {
        	if(!(data instanceof Array)) {
        		return;
        	}
    		data.forEach((book) => {
    			state.alerts.push({
    				success: true, 
    				msg: 'O livro "' + book.name + '" foi deletado com sucesso!'
    			});
    		});
        },
        SHOW_CANT_SAVE_MSG(state) {
        	state.alerts.push({error: true, msg: 'Não é possível salvar o livro!'});
        }
	}
	
	const refreshMutations = {
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
        }
	}
	
	var mutations = {}
	
	Object.assign(mutations, refreshMutations, messageMutations, editMutations);
	
	const bookSaveActions = {
		saveNewBook(context, newBook) {
			return new Promise((resolve, reject) => {
        		commonFunctions.saveBook({context, resolve, reject}, newBook);
        	})
        },
        save(context, editedBook) {
        	return new Promise((resolve, reject) => {
        		commonFunctions.saveBook({context, resolve, reject}, editedBook, true);
        	})
        }
	}
	
	const bookEditActions = {
		toggleEditBook(context) {
        	var selectedBooks = this.getters.selectedBooks;
        	if(selectedBooks.length == 1) {
        		context.commit('TOGGLE_EDIT_BOOK');
        	} else {
        		context.commit('STOP_EDITING');
        	}
        },
        stopEditing(context) {
        	context.commit('STOP_EDITING');
        }
	}
	
	const bookRemoveActions = {
		removeBook(context, book) {
        	if(!book || !book.id) {
        		return;
        	} 
        	commonFunctions.removeBooks(context, [book.id]);
        },
        removeBooks(context) {
        	var ids = [];
			this.getters.selectedBooks.forEach((livro) => ids.push(livro.id));
			if(ids.length == 0) {
				return;
			}
			commonFunctions.removeBooks(context, ids);
		}
	}
	
	const refreshActions = {
		refreshBooks(context) {
            axios.get('http://localhost:8080/books').then((response) => {
                context.commit('REFRESH_BOOKS', response.data);
            })
        }
	}
	
	var actions = {}
	
	Object.assign(actions, refreshActions, bookSaveActions, bookEditActions, bookRemoveActions);
	
	var store = new Vuex.Store({
	    state: data,
	    getters,
	    mutations,
	    actions
	});
	
	export default store
</script>