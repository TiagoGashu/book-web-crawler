 <template>
 	<div v-show="isActive">
	    <div style="max-height: 255px; overflow: auto;">
	        <ul class="list-group"> 
                <li class="list-group-item" v-for="livro in livros" :class="{'active': livro.checked }"> 
                    <div class="checkbox"> 
                        <input type="checkbox" v-model="livro.checked"> 
                        {{ livro.name }} - 
                        <small v-for="author in livro.authors">
                        	{{ author.name }}
                        </small> 
                        <span>id: {{livro.id}}</span>
                    </div>  
                </li> 
            </ul>
        </div>
        <edit-book-comp></edit-book-comp>
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
		computed: {
			livros() {
				return this.$store.getters.livros;
			}
		},
		mounted: function() {
			store.dispatch('refreshBooks')
		}
 	}
 </script>
 