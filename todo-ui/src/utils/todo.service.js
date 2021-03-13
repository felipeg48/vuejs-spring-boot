import Axios from 'axios';

Axios.defaults.baseURL = process.env.ROOT_API;

const RESOURCE_NAME = '/todos';

export default {
  getAll() {
    return Axios.get(RESOURCE_NAME);
  },
  get(id) {
    return Axios.get(`${RESOURCE_NAME}/${id}`);
  },
  create(data) {
    return Axios.post(RESOURCE_NAME, data);
  },
  update(id, data) {
    return Axios.put(`${RESOURCE_NAME}/${id}`, data);
  },
  delete(id) {
    return Axios.delete(`${RESOURCE_NAME}/${id}`);
  }
};
