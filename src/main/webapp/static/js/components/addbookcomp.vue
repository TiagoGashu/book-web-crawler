<template>
	<div class="editing-container" v-show="isActive">
        <div class="input-group">
            <div class="input-group-prepend mb-3">
               <span class="input-group-text">Book&apos;s name</span>
            </div>
            <input v-model="newBook.name" @keyup.enter="addLivro"
                type="text" class="form-control" style="height: 38px;"/>
        </div>
        <div class="input-group">
            <div class="input-group-prepend mb-3">
               <span class="input-group-text">Author&apos;s name</span>
            </div>
            <input v-model="newBook.authorName" @keyup.enter="addLivro"
                type="text" class="form-control" style="height: 38px;"/>
        </div>
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
	import services from '../services/services.vue'
	
	var data = {
		newBook: {
			name: '',
			authorName: ''
		},
		componentName: C.components.AddBookComp
	};
	
	var vuexObj = {
		actions: {
			dispatchAddBook({dispatch}, newBook) {
				dispatch('saveNewBook', newBook)
			}
		}
	}
	
	export default {
		data() {
			return data;
		},
		mixins: [navigationmixin],
		vuex: vuexObj,
		computed: {
		    fieldsDefined() {
		    	return this.newBook.name != '' && this.newBook.authorName != '';
		    }
		},
		methods: {
			addBook() {
				var newBook = services.copyBook(this.newBook);
        		this.$store.dispatch('saveNewBook', newBook);
        		// estado interno do componente
        		this.newBook.name = '';
        		this.newBook.authorName = '';
			}
		}
	}
</script>
