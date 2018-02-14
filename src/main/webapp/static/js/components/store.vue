<script>
	/** store do vuex */
	import axios from 'axios'
	import Vue from 'vue'
	import Vuex from 'vuex'
	import data from './data.vue'

	Vue.use(Vuex)
	
	var store = new Vuex.Store({
	    state: data,
	    mutations: {
	        updateLivros(state, responseJson) {
	            var livros = this.state.livros;
	            var livrosRemover = this.state.livrosRemover;
	            livros.splice(0, livros.length);
	            responseJson.forEach(function(livro) {
	                livros.push(livro);
	            })
	            livrosRemover.splice(0, livrosRemover.length);
	        }
	    },
	    actions: {
	        refreshLivros(context) {
	            return new Promise((resolve) => {
	                axios.get('http://localhost:8080/books').then((response) => {
	                    context.commit('updateLivros', response.data);
	                })
	            })
	        }
	    }
	});
	
	export default store
</script>