<template>
	<div class="editing-container">
        <book :book="bookEditing"></book>
        
        <div v-show="canSave">
        	<button class="btn btn-primary" type="button" @click="save"> 
            	Save
        	</button>
        </div>
    </div>
</template>

<script>
	/** componente de edição de livro */
	import C from '../constants.vue'
	import Vue from 'vue'
	import store from '../vuex/store.vue'
	import utils from '../services/utils.vue'
	
	var data = {
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
		props: ['bookEditing'],
    	methods: {
	    	resetBook() {
	    		utils.copyBook(this.emptyBook, this.bookEditing);
	    	},
	    	_canSave() {
	    		return utils.canSaveBook(this.bookEditing);
	    	},
	    	save() {
	    		if(!this._canSave()) {
	    			return;
	    		}
	    		var editedBook = {}
	    		utils.copyBook(this.bookEditing, editedBook);
	    		this.$store.dispatch('save', editedBook).then(response => {
					this.resetBook();
	    		}, error => {
					//	    		
	    		});
	    	}
		},
		computed: {
			canSave() {
				return this._canSave();
			},
			editing() {
				return this.$store.getters.editing;
			}
		}
	}
</script>