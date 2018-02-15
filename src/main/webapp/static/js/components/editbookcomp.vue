<template>
	<div v-show="hasBookToEdit">
        <div class="editing-container" :class="{'not-editing': !editing}" v-if="editing">
            <div style="text-align: center;">
                <big>Book Editing</big>
            </div>
            
            <book :book="bookEditing"></book>
            
            <div v-show="canSave">
            	<button class="btn btn-primary" type="button" @click="save"> 
                	Save
            	</button>
            </div>
        </div>
        <button class="btn btn-primary" type="button" @click="toggleEditBook"
             style="margin-top: 2px;">{{ editing ? 'Disable Edit' : 'Enable Edit' }}</button>
    </div>
</template>

<script>
	/** componente de edição de livro */
	import C from '../constants.vue'
	import Vue from 'vue'
	import store from '../vuex/store.vue'
	import utils from '../services/utils.vue'
	
	var data = {
		bookEditing: {
			name: '',
			authors: [],
			genres: []
		},
		emptyBook: {
			name: '',
			authors: [],
			genres: []
		}
	}
	
	export default {
		data() {
			return data;
		},
    	methods: {
    		toggleEditBook() {
	    		if(this.$store.getters.selectedBooks.length == 1) {
    				this.$store.dispatch('toggleEditBook');
    			}
	    	},
	    	resetBook() {
	    		utils.copyBook(this.emptyBook, this.bookEditing);
	    	},
	    	loadBookToEdit() {
	    		// ja estou editando
				if(this.bookEditing.id != null) {
					return this.bookEditing;
				}
				
				var bookToEdit = this.$store.getters.selectedBooks[0];
				utils.copyBook(bookToEdit, this.bookEditing);
	    	},
	    	_canSave() {
	    		return this.editing && utils.isNotEmpty(this.bookEditing.name) && 
					utils.isNotEmpty(this.bookEditing.authorName) && this.bookEditing.id != null;
	    	},
	    	save() {
	    		if(!this._canSave()) {
	    			return;
	    		}
	    		var editedBook = {}
	    		utils.copyBook(this.bookEditing, editedBook);
	    		this.$store.dispatch('save', editedBook);
	    		this.resetBook();
	    	}
		},
		computed: {
			hasBookToEdit() {
				return this.$store.getters.selectedBooks.length == 1;
			},
			canSave() {
				return this._canSave();
			},
			editing() {
				if(this.$store.getters.editing) {
					this.loadBookToEdit();
				} else {
					this.resetBook();
				}
				return this.$store.getters.editing;
			}
		}
	}
</script>