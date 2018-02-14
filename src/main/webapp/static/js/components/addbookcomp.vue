<template>
	<div class="editing-container" v-show="isActive">
        <div class="input-group">
            <div class="input-group-prepend mb-3">
               <span class="input-group-text">Book&apos;s name</span>
            </div>
            <input v-model="root.novoLivro.name" @keyup.enter="addLivro"
                type="text" class="form-control" style="height: 38px;"/>
        </div>
        <div class="input-group">
            <div class="input-group-prepend mb-3">
               <span class="input-group-text">Author&apos;s name</span>
            </div>
            <input v-model="root.novoLivro.authorName" @keyup.enter="addLivro"
                type="text" class="form-control" style="height: 38px;"/>
        </div>
        <div class="input-group-btn" v-show="fieldsDefined"> 
            <button @click="addLivro" class="js-add btn btn-primary"
                type="button">Add Book</button> 
        </div>
    </div>
</template>

<script>
	/** componente para adição de livros */
	import C from './constants.vue'
	import Vue from 'vue'
	import store from './store.vue'
	import services from './services.vue'
	import navigationmixin from './navigationmix.vue'
	
	var data = {
		root: store.state,
		componentName: C.components.AddBookComp
	};
	
	export default {
		mixins: [navigationmixin],
		data() {
			return data;
		},
		computed: {
		    fieldsDefined() {
		    	return this.root.novoLivro.name != '' && this.root.novoLivro.authorName != '';
		    }
		},
		methods: {
			addLivro() {
				var novoLivro = this.root.novoLivro;
        		services.saveBook(novoLivro);
        		this.root.novoLivro = {}
			}
		}
	}
</script>
