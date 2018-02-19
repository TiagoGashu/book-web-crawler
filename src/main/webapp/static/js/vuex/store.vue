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
		mangas: state => state.mangas,
		selectedBooks: state => state.livros.filter((l) => l.checked),
		selectedMangas: state => state.mangas.filter((m) => m.checked),
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
		saveManga(params, editedManga, stopEditing) {
			var context, resolve, reject;
			({context, resolve, reject} = params);
			if(!utils.canSaveManga(editedManga)) {
				context.commit('SHOW_CANT_SAVE_MSG');
				reject();	
				return
			}
			axios.post('http://localhost:8080/mangas', editedManga).then((response) => {
				resolve();
				if(stopEditing) {
					context.commit('STOP_EDITING');
				}
				context.commit('SHOW_SAVE_SUCCESS_MSG', response.data);
				context.dispatch('refreshMangas');
			});
		},
		removeBooks(context, ids) {
			var bookIdsParam = 'bookIds=' + ids.join(',');
			axios.delete('http://localhost:8080/books?' + bookIdsParam).then(function(response) {
				context.commit('SHOW_REMOVE_SUCCESS_MSG', response.data);
				context.dispatch('refreshBooks', response.data);
			})
		},
		removeManga(context, ids) {
			var bookIdsParam = 'mangaIds=' + ids.join(',');
			axios.delete('http://localhost:8080/mangas?' + mangaIdsParam).then(function(response) {
				context.commit('SHOW_REMOVE_SUCCESS_MSG', response.data);
				context.dispatch('refreshMangas', response.data);
			})
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
        },
        REFRESH_MANGAS(state, responseJson) {
        	if(!state.mangas) {
				state.mangas = [];
			}
            var mangas = state.mangas;
            var mangasRemover = this.getters.selectedMangas;
            mangas.splice(0, mangas.length);
            responseJson.forEach(function(manga) {
                mangas.push(manga);
            })
        }
	}
	
	const editMutations = {
		TOGGLE_EDIT(state) {
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
	
	const modalConfirmMutations = {
		SHOW_MODAL_CONFIRM(state, params) {
			state.modalConfirm.showModal = true;
			if(params.title) {
				state.modalConfirm.title = params.title
			}
			if(params.bodyMessage) {
				state.modalConfirm.bodyMessage = params.bodyMessage
			}
			if(params.confirmLabel) {
				state.modalConfirm.confirmLabel = params.confirmLabel;
			}
			if(params.cancelLabel) {
				state.modalConfirm.cancelLabel = params.cancelLabel;
			}
			state.modalConfirm.confirmCallback = params.confirmCallback;
			state.modalConfirm.cancelCallback = params.cancelCallback;
		},
		CLOSE_MODAL_CONFIRM(state) {
			state.modalConfirm.showModal = false;
			state.modalConfirm.title = '';
			state.modalConfirm.bodyMessage = '';
			state.modalConfirm.confirmLabel = '';
			state.modalConfirm.cancelLabel = '';
			state.modalConfirm.confirmCallback = function(){};
			state.modalConfirm.cancelCallback = function(){};
		}
	}
	
	const loadFromSourcesMutations = {
		LOAD_MANGA_INFO_FROM_SOURCE(state, params) {
			var manga = params.manga;
			var mangaNewInfos = params.data;
			if(mangaNewInfos.genres && mangaNewInfos.genres.length > 0) {
				mangaNewInfos.genres.forEach((g) => manga.genres.push(g));
			}
			if(mangaNewInfos.authors && mangaNewInfos.authors.length > 0) {
				mangaNewInfos.authors.forEach((a) => manga.authors.push(a));
			}
			if(mangaNewInfos.mangaChapters && mangaNewInfos.mangaChapters.length > 0) {
				mangaNewInfos.mangaChapters.forEach((c, i) => {
					if(!c.pages) {
						c.pages = [];
					}
					if(!c.ordem) {
						c.ordem = i;
					}
					var found = manga.mangaChapters.filter((oldC) => {
						return oldC.chapterName == c.chapterName;
					});
					
					if(!found || found.length == 0) {
						manga.mangaChapters.push(c)
					}
				});
			}
		}
	}
	
	var mutations = {}
	
	Object.assign(mutations, refreshMutations, messageMutations, 
		editMutations, modalConfirmMutations, loadFromSourcesMutations);
	
	const bookSaveActions = {
		saveNewBook(context, newBook) {
			return new Promise((resolve, reject) => {
        		commonFunctions.saveBook({context, resolve, reject}, newBook);
        	})
        },
        saveBook(context, editedBook) {
        	return new Promise((resolve, reject) => {
        		commonFunctions.saveBook({context, resolve, reject}, editedBook, true);
        	})
        }
	}
	
	const mangaSaveActions = {
		saveNewManga(context, newManga) {
			return new Promise((resolve, reject) => {
        		commonFunctions.saveManga({context, resolve, reject}, newManga);
        	})
        },
        saveManga(context, editedManga) {
        	return new Promise((resolve, reject) => {
        		commonFunctions.saveManga({context, resolve, reject}, editedManga, true);
        	})
        }
	}
	
	const editActions = {
		stopEditing(context) {
        	context.commit('STOP_EDITING');
        }
	}
	
	const bookEditActions = {
		toggleEditBook(context) {
        	var selectedBooks = this.getters.selectedBooks;
        	if(selectedBooks.length == 1) {
        		context.commit('TOGGLE_EDIT');
        	} else {
        		context.commit('STOP_EDITING');
        	}
        }
	}
	
	const mangaEditActions = {
		toggleEditManga(context) {
        	var selectedMangas = this.getters.selectedMangas;
        	if(selectedMangas.length == 1) {
        		context.commit('TOGGLE_EDIT');
        	} else {
        		context.commit('STOP_EDITING');
        	}
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
	
	const mangaRemoveActions = {
		removeBook(context, manga) {
        	if(!manga || !manga.id) {
        		return;
        	} 
        	commonFunctions.removeMangas(context, [manga.id]);
        },
        removemangas(context) {
        	var ids = [];
			this.getters.selectedMangas.forEach((manga) => ids.push(manga.id));
			if(ids.length == 0) {
				return;
			}
			commonFunctions.removeMangas(context, ids);
		}
	}
	
	const refreshActions = {
		refreshBooks(context) {
            axios.get('http://localhost:8080/books').then((response) => {
                context.commit('REFRESH_BOOKS', response.data);
            })
        },
        refreshMangas(context) {
            axios.get('http://localhost:8080/mangas').then((response) => {
                context.commit('REFRESH_MANGAS', response.data);
            })
        }
	}
	
	var modalConfirmActions = {
		showModalConfirm(context, params) {
			context.commit('SHOW_MODAL_CONFIRM', params);
		},
		closeModalConfirm(context) {
			context.commit('CLOSE_MODAL_CONFIRM');
		}
	}
	
	var loadFromSourceActions = {
		loadMangaInfoFromSource(context, manga) {
			var sourceUrl = manga.source
			axios.get('http://localhost:3000/mangaChapters?url=' + sourceUrl, {
				method: 'GET',
				crossDomain: true,
			}).then((response) => {
				context.commit('LOAD_MANGA_INFO_FROM_SOURCE', {manga: manga, data: response.data});
			})
		}
	}
	
	var actions = {}
	
	Object.assign(actions, refreshActions, 
		bookSaveActions, mangaSaveActions, 
		editActions, bookEditActions, mangaEditActions, 
		bookRemoveActions, mangaRemoveActions, 
		modalConfirmActions,
		loadFromSourceActions);
	
	var store = new Vuex.Store({
	    state: data,
	    getters,
	    mutations,
	    actions
	});
	
	export default store
</script>