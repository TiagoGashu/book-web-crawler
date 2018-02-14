<template>
	<div v-show="hasBookToEdit">
        <div class="editing-container" :class="{'not-editing': !editing}" v-if="editing">
            <div style="text-align: center;">
                <big>Book Editing</big>
            </div>
            <div class="editBook">
                <div class="input-group">
                    <div class="input-group-prepend mb-3">
                    <span class="input-group-text">Book&apos;s name</span>
                    </div>
                    <input v-model="bookEditing.name" @keyup.enter="save"
                  type="text" class="form-control" style="height: 38px;"/>
                </div>
                <div class="input-group">
                    <div class="input-group-prepend mb-3">
                        <span class="input-group-text">Author&apos;s name</span>
                    </div>
                    <input v-model="bookEditing.authorName" @keyup.enter="addLivro"
                      type="text" class="form-control" style="height: 38px;"/>
                </div>
            </div>
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
	import services from '../services/services.vue'
	
	var data = {
		bookEditing: {
			name: '',
			authorName: ''
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
	    		this.bookEditing = {name: '', authorName: ''}
	    	},
	    	loadBookToEdit() {
	    		// ja estou editando
				if(this.bookEditing.id != null) {
					return this.bookEditing;
				}
				
				var bookToEdit = this.$store.getters.selectedBooks[0];
				this.bookEditing = services.copyBook(bookToEdit);
	    	},
	    	_canSave() {
	    		return this.editing && services.isNotEmpty(this.bookEditing.name) && 
					services.isNotEmpty(this.bookEditing.authorName) && this.bookEditing.id != null;
	    	},
	    	save() {
	    		if(!this._canSave()) {
	    			return;
	    		}
	    		var editedBook = services.copyBook(this.bookEditing);
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