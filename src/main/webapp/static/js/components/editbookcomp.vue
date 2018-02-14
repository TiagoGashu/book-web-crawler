<template>
	<div v-show="livrosRemover.length == 1">
        <div class="editing-container" :class="{'not-editing': !editing}" v-show="editing">
            <div style="text-align: center;">
                <big>Book Editing</big>
            </div>
            <div class="editBook">
                <div class="input-group">
                    <div class="input-group-prepend mb-3">
                    <span class="input-group-text">Book&apos;s name</span>
                    </div>
                    <input v-model="editLivro.name" @keyup.enter="save"
                  type="text" class="form-control" style="height: 38px;"/>
                </div>
                <div class="input-group">
                    <div class="input-group-prepend mb-3">
                        <span class="input-group-text">Author&apos;s name</span>
                    </div>
                    <input v-model="editLivro.authorName" @keyup.enter="addLivro"
                      type="text" class="form-control" style="height: 38px;"/>
                </div>
            </div>
            <button class="btn btn-primary" type="button" @click="save"  
               v-show="editing && editLivro.name && editLivro.authorName"> 
                Save
            </button>
        </div>
        <button class="btn btn-primary" type="button" @click="toggleEditBook"
             style="margin-top: 2px;">{{ editing ? 'Disable Edit' : 'Enable Edit' }}</button>
    </div>
</template>

<script>
	/** componente de edição de livro */
	import C from './constants.vue'
	import Vue from 'vue'
	import store from './store.vue'
	
	export default {
		data: function() {
			return store.state;
		},
    	methods: {
    		toggleEditBook() {
	    		if(this.editing) {
	    			this.editLivro = {name: '', authorName: ''}
	    		}
	    		this.editing = !this.editing;
	    		
	    		if(this.editing && this.livrosRemover.length == 1) {
	    			var livroSendoEditado = this.livrosRemover[0];
	    			this.editLivro.id = livroSendoEditado.id
	    			this.editLivro.name = livroSendoEditado.name
	    			this.editLivro.authorName = livroSendoEditado.authorName
	    		}
	    	},
	    	save() {
	    		var editLivro = this.editLivro;
	    		saveBook(editLivro);
	    		this.editing = false;
	    		this.editLivro = {};
	    	}
		}
	}
</script>