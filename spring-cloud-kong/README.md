Leader election mechanism uses Kubernetes ConfigMap feature to coordinate leadership information. To access ConfigMap user needs correct role and role binding. Create them using the following commands:

```
kubectl apply -f leader-role.yml
kubectl apply -f leader-rolebinding.yml
```

Build image through command
```
 ./gradlew bootBuildImage -Dspring-boot.build-image.imageName=org/kubernetes-select-leader
```
Or
```
docker build -t kubernetes-select-leader . -f ./Dockerfile
```
Use this command to deploy to Kubernetes
```
kubectl create -f deploy/deploy.yaml
```
Delete deploy
```
kubectl delete -f deploy/deploy.yaml
```