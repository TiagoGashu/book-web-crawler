 <template>
 	<div v-show="isActive" class="view-books-comp">
	    <div style="max-height: 80%; overflow: auto;">
	    
	        <ul class="list-group" :class="{'editing' : _editing}">
	        
                <li class="list-group-item" v-for="livro in livros" :class="{'active': livro.checked }"> 
                    <div class="checkbox"> 
                        <input type="checkbox" v-model="livro.checked" @change="cancelEditing">
                        
                        {{ livro.name }} - 
                        <small v-for="author in livro.authors">
                        	{{ author.name }}
                        </small>
                        <span>id: {{livro.id}}</span>
                        
                        <span v-if="canEdit(livro)" @click="toggleEdit" :title="editButtonTitle" class="button">
                        	<i class="material-icons">create</i>
                        </span>
                        <span v-if="canRemove(livro)" @click="remove(livro)" title="remove book" class="button">
                        	<i class="material-icons">delete_sweep</i>
                        </span>
                    </div>
                    <div class="editArea" v-if="editing(livro)">
                    	<edit-book-comp :bookEditing="livro"></edit-book-comp>
                    </div>
                </li>
                
            </ul>
            
            <div class="remove-all button" v-if="moreThanOne()">
            	<i @click="removeAll" class="material-icons md-48" :title="removeAllTitle">delete_sweep</i>
            </div>
        </div>
    </div>
 </template>
 
 <script>
 	/** componente para exibição e seleção de livros */
 	import C from '../constants.vue'
 	import store from '../vuex/store.vue'
 	import navigationmix from '../services/navigationmix.vue'
 	
 	var data = {
 		componentName: C.components.ViewBooksComp 
 	}
 	
 	export default {
 		mixins: [navigationmix],
 		data() {
 			return data;
 		},
 		methods: {
 			onlyOne() {
 				return this.$store.getters.selectedBooks.length == 1
 			},
 			moreThanOne() {
 				return this.$store.getters.selectedBooks.length > 1;
 			},
 			canEdit(book) {
 				return this.onlyOne() && book.checked;
 			},
 			canRemove(book) {
 				return this.onlyOne() && book.checked;
 			},
 			cancelEditing() {
 				if(!this.onlyOne()) {
 					this.$store.dispatch('stopEditing');	
 				}
 			},
 			toggleEdit() {
 				this.$store.dispatch('toggleEditBook');
 			},
			editing(book) {
				return this.$store.getters.editing && book.checked;	
			},
			remove(book) {
				this.$store.dispatch('removeBook', book);
			},
 			removeAll() {
 				this.$store.dispatch('removeBooks');
 			}
 		},
		computed: {
			livros() {
				return this.$store.getters.livros;
			},
			_editing() {
				return this.$store.getters.editing;
			},
			editButtonTitle() {
				return this._editing ? 'stop editing' : 'edit book';
			},
			removeAllTitle() {
				return 'delete all selected: ' + this.$store.getters.selectedBooks.map(el => el.name).join(',');
			}
		},
		mounted: function() {
			store.dispatch('refreshBooks')
		}
 	}
 </script>
 
<style>
	.view-books-comp .list-group-item .button {
		position: relative;
		top: 4px;
	}
	.md-48 {
		font-size: 48px;
	}
	.view-books-comp .editing .list-group-item.active {
		background-color: #b4b9bf;
	}
	.list-group .button {
		cursor: pointer;
	}
	.remove-all {
		position: absolute;
		margin-top: 11px;
    	right: 13px;
    	cursor: pointer;
	}
 </style>