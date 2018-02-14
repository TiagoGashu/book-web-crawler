 <template>
 	<div class="remove-book-comp-margin-top" v-show="isActive">
        <button class="btn btn-primary" type="button"@click="toggleRemoveAreaVisible">
            Toogle remove Area
        </button>
        <div id="removeArea" style="text-align: center;" v-show="isShowingRemoveArea">
            <ul class="list-group">
                <li class="list-group-item"> 
                    - Book{{plural}} to remove - 
                </li>
                <li class="list-group-item" v-for="livro in livrosRemover">
                    {{livro.name}} - {{livro.authorName}}
                </li>
            </ul>
            <div v-show="livrosRemover.length > 0" 
                    style="margin-top: 6px; position: absolute; right: 0; margin-right: 15px;">
                <div class="col-sm col-sm-offset-10">
                    <button@click="removeBooks" class="js-remove btn btn-primary"
                      type="button">Remove Book{{plural}}</button>
                </div>
            </div>
        </div>
    </div>
 </template>
 
 <script>
 	/** componente para remoção de livros */
 	import C from '../constants.vue'
 	import navigationmix from '../services/navigationmix.vue'
 	import Vue from 'vue'
 	import store from '../vuex/store.vue'
 	
 	var data = {
		componentName: C.components.ViewBooksComp
 	}
 	
 	export default {
		mixins: [navigationmix],
		data: function() {
			return data;
		},
		methods: {
			toggleRemoveAreaVisible() {
				this.$store.dispatch('toggleRemoveAreaVisible');
			},
			removeBooks: function() {
				this.$store.dispatch('removeBooks');
			}
		},
		computed: {
			isShowingRemoveArea() {
				return this.$store.getters.removeAreaVisible;
			},
			livrosRemover() {
				return this.$store.getters.selectedBooks;
			},
			plural() {
				if(this.$store.getters.selectedBooks.length > 1) {
					return 's';
				} else {
					return '';
				}
			}
		}
	}
</script>

<style>
	.remove-book-comp-margin-top{
		margin-top: 16px;
	}
</style>