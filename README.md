<!-- Got it ✅
Here’s the **AZURE-CHEAT-SHEET.md** updated with a simple ASCII flow diagram so you can quickly visualize the process without reading every command. -->

---

````markdown
# 🚀 Azure Cheat Sheet – Quick Reference

A one-page guide for setting up Azure resources, linking them with GitHub & local dev, and remembering key commands.

---

## 🗺 Quick Flow Diagram

```plaintext
 ┌────────────────────┐
 │ 1. Create Resource  │
 │    Group            │
 └───────┬─────────────┘
         │
 ┌───────▼─────────────┐
 │ 2. Create Managed   │
 │    Identity         │
 └───────┬─────────────┘
         │
 ┌───────▼─────────────┐
 │ 3. App Registration │
 └───────┬─────────────┘
         │
 ┌───────▼─────────────┐
 │ 4. Assign IAM Role  │
 │    to App           │
 └───────┬─────────────┘
         │
 ┌───────▼─────────────┐
 │ 5. Register Azure   │
 │    Provider         │
 └───────┬─────────────┘
         │
 ┌───────▼─────────────┐
 │ 6. Link GitHub via  │
 │    Service Principal│
 └───────┬─────────────┘
         │
 ┌───────▼─────────────┐
 │ 7. Login Locally    │
 └─────────────────────┘
```
````

---

## 1️⃣ Create a Resource Group

**Purpose:** A logical container for your Azure resources.

```bash
az group create --name <RESOURCE_GROUP_NAME> --location <LOCATION>
```

---

## 2️⃣ Create a Managed Identity

**Purpose:** Gives an Azure resource an automatically managed identity in Azure AD.

```bash
az identity create --name <IDENTITY_NAME> --resource-group <RESOURCE_GROUP_NAME>
```

---

## 3️⃣ Create an App Registration

**Purpose:** Registers your application in Azure AD for authentication & API access.

```bash
az ad app create --display-name <APP_NAME>
```

> Output will contain the **App ID (Client ID)** and **Object ID**.

---

## 4️⃣ Assign App Registration to Resource Group IAM

**Purpose:** Grants your app permissions to manage resources.

```bash
az role assignment create \
  --assignee-object-id <APP_OBJECT_ID> \
  --role "Contributor" \
  --resource-group <RESOURCE_GROUP_NAME>
```

---

## 5️⃣ Register Subscription for a Service

**Purpose:** Enable a specific Azure resource provider before using it.

```bash
az provider register --namespace Microsoft.Storage
# Example: For PostgreSQL
az provider register --namespace Microsoft.DBforPostgreSQL
```

---

## 6️⃣ Link Azure to GitHub Actions

**Purpose:** Allow GitHub Actions to deploy to Azure.

1. Create a **Service Principal**:

   ```bash
   az ad sp create-for-rbac \
     --name github-deploy \
     --role contributor \
     --scopes /subscriptions/<SUBSCRIPTION_ID>/resourceGroups/<RESOURCE_GROUP_NAME> \
     --sdk-auth
   ```

2. Copy JSON output → Add to GitHub **Secrets** as `AZURE_CREDENTIALS`.

---

## 7️⃣ Login from Local Machine

**Purpose:** Authenticate CLI locally without storing credentials.

```bash
az login --use-device-code
```

> Opens a link where you enter the device code for authentication.

---

## 8️⃣ Useful Role Assignment Command

**Purpose:** Directly assign a role to a user/service principal by Object ID.

```bash
az role assignment create \
  --assignee-object-id <OBJECT_ID> \
  --role Contributor \
  --resource-group <RESOURCE_GROUP_NAME>
```

---

## 📌 Tips

- **Always** check provider registration before creating a resource:

  ```bash
  az provider list --query "[?registrationState=='NotRegistered']"
  ```

- Store Object IDs & App IDs in a secure place.
- Use `az account show` to verify you’re in the correct subscription.

---

**Author:** Your Future Self
**Purpose:** So you never forget the setup flow again.

```

---

```
