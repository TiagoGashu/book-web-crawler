<template>
	<div class="editing-container" v-show="isActive">
        
        <book :book="newBook"></book>
        
        <div class="input-group-btn" v-show="fieldsDefined"> 
            <button @click="addBook" class="js-add btn btn-primary"
                type="button">Add Book</button> 
        </div>
    </div>
</template>

<script>
	/** componente para adição de livros */
	import C from '../constants.vue'
	import Vue from 'vue'
	import store from '../vuex/store.vue'
	import navigationmixin from '../services/navigationmix.vue'
	import utils from '../services/utils.vue'
	
	var data = {
		newBook: {
			name: '',
			authors: [],
			genres: []
		},
		emptyBook: {
			name: '',
			authors: [],
			genres: []
		},
		componentName: C.components.AddBookComp
	};
	
	export default {
		data() {
			return data;
		},
		mixins: [navigationmixin],
		computed: {
		    fieldsDefined() {
		    	return this.newBook.name != '' && this.newBook.authorName != '';
		    },
		},
		methods: {
			addBook() {
				var newBook = {};
				utils.copyBook(this.newBook, newBook);
        		this.$store.dispatch('saveNewBook', newBook).then(response => {
        			utils.copyBook(this.emptyBook, this.newBook);
        		}, error => {
        			// 
        		});
			}
		}
	}
</script>
